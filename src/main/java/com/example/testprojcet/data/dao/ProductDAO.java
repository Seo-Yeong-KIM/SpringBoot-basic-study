package com.example.testprojcet.data.dao;

import com.example.testprojcet.data.entity.ProductEntity;

public interface ProductDAO {

    ProductEntity saveProduct(ProductEntity productEntity);

    ProductEntity getProduct(String productId);

}