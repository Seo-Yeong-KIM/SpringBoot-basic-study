package com.example.testprojcet.data.handler;

import com.example.testprojcet.data.entity.ProductEntity;

public interface ProductDataHandler {

    ProductEntity saveProductEntity(String productId, String productName, int productPrice, int productStock);

    ProductEntity getProductEntity(String productId);

}
