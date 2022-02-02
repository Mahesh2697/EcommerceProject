package com.AccountService.Account.service;

import com.AccountService.Account.entity.InventoryEntity;
import com.AccountService.Account.entity.PriceEntity;
import com.AccountService.Account.entity.ProductEntity;
import com.AccountService.Account.entity.SkusEntity;
import com.AccountService.Account.model.InventoryModel;
import com.AccountService.Account.model.PriceModel;
import com.AccountService.Account.model.ProductModel;
import com.AccountService.Account.model.SkusModel;
import com.AccountService.Account.repository.ProductRepository;
import com.AccountService.Account.repository.SkusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SkusRepository skusRepository;

    //conversion of model to entity to add products //
    public ProductEntity addProduct(ProductModel productModel) {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductCode(productModel.getProductCode());
        productEntity.setProductName(productModel.getProductName());
        productEntity.setDescription(productModel.getDescription());
        List<SkusEntity> skusEntityList = new ArrayList<>();
        productEntity.setSkuEntityList(skusEntityList);
       return productRepository.save(productEntity);

    }
    //conversion  of model to entity and if the product is present adding price to a particular product //
    public ProductEntity addSkus(String productCode, SkusModel skusModel){
        ProductEntity productEntity1 = productRepository.findByProductCode(productCode);
        if(productEntity1!=null){
            List<SkusEntity> skusEntityList = new ArrayList<>();
            SkusEntity skusEntity = new SkusEntity();
            skusEntity.setSkuCode(skusModel.getSkuCode());
            skusEntity.setSize(skusModel.getSize());
            skusEntity.setProductEntity(productEntity1);
            skusEntityList.add(skusEntity);
            productEntity1.setSkuEntityList(skusEntityList);
            return productRepository.save(productEntity1);
        }
        return null;

    }
    //conversion of model to entity for adding price to a particular//
    public  SkusEntity addPrice(String skuCode, PriceModel priceModel){
        SkusEntity skusEntity =  skusRepository.findBySkuCode(skuCode);
        if(skusEntity!=null){
            PriceEntity priceEntity = new PriceEntity();
            priceEntity.setPrice(priceModel.getPrice());
            priceEntity.setCurrency(priceModel.getCurrency());
            priceEntity.setSkusEntity(skusEntity);
            skusEntity.setPriceEntity(priceEntity);
          return  skusRepository.save(skusEntity);
        }
        return  null;
    }
    //conversion of model to entity for adding stock for particular sku//
    public String addStock(String skuCode, InventoryModel inventoryModel) {
        SkusEntity sku = skusRepository.findBySkuCode(skuCode);
        if(sku!=null){
            InventoryEntity inventoryEntity = new InventoryEntity();
            inventoryEntity.setQuantityAvailable(inventoryModel.getQuantityAvailable());
            inventoryEntity.setSkusEntities(sku);
            sku.setInventoryEntity(inventoryEntity);
            skusRepository.save(sku);
            return  "inventory added";
        }
        return  "sku not available";
    }


    //this method will give all the products,skus,price and  available quantity in inventory
    private ProductModel getAllProducts(ProductEntity productEntity){
        ProductModel productModel = new ProductModel();
        productModel.setProductCode(productEntity.getProductCode());
        productModel.setProductName(productEntity.getProductName());
        productModel.setDescription(productEntity.getDescription());

        List<SkusModel> skusModel = new ArrayList<>();
        productEntity.getSkuEntityList().forEach(skusEntity-> {
            SkusModel skus = new SkusModel();
            skus.setSkuCode(skusEntity.getSkuCode());
            skus.setSize(skusEntity.getSize());
            productModel.setSkusModelList(skusModel);
            skusModel.add(skus);

            PriceModel priceModel= new PriceModel();
            priceModel.setPrice(skusEntity.getPriceEntity().getPrice());
            priceModel.setCurrency(skusEntity.getPriceEntity().getCurrency());
            skus.setPriceModel(priceModel);

            InventoryModel inventoryModel = new InventoryModel();
            inventoryModel.setQuantityAvailable(skusEntity.getInventoryEntity().getQuantityAvailable());
            skus.setPriceModel(priceModel);
            skus.setInventoryModel(inventoryModel);

        });

        return productModel;
    }

    //this method will give list of products
    public List<ProductModel> getProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
       return productEntities.stream().map(product-> getAllProducts(product)).collect(Collectors.toList());
    }
}
