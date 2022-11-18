package com.example.testprojcet.data.handler.impl;

import com.example.testprojcet.data.dao.ProductDAO;
import com.example.testprojcet.data.dto.ProductDTO;
import com.example.testprojcet.data.entity.ProductEntity;
import com.example.testprojcet.data.handler.ProductDataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductDataHandlerImlpl implements ProductDataHandler {

    ProductDAO productDAO;

    @Autowired
    public ProductDataHandlerImlpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public ProductEntity saveProductEntity(String productId, String productName, int productPrice, int productStock) {

        ProductEntity product = new ProductEntity(productId, productName, productPrice,
                productStock);
        return productDAO.saveProduct(product);
    }

    @Override
    public ProductEntity getProductEntity(String productId) {
        return productDAO.getProduct(productId);
    }
}
