package com.example.testprojcet.data.dao.impl;

import com.example.testprojcet.data.dao.ProductDAO;
import com.example.testprojcet.data.entity.ProductEntity;
import com.example.testprojcet.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDAOImpl implements ProductDAO {

    // Repository를 필드 값으로 가짐
    ProductRepository productRepository;

    // 생성자
    @Autowired
    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductEntity saveProduct(ProductEntity productEntity) {
        productRepository.save(productEntity); // 이때 구현한 .save()메서드는 JpaRepository클래스의 메서드
        return productEntity;
    }

    @Override
    public ProductEntity getProduct(String productId) {
        ProductEntity productEntity = productRepository.getById(productId); // 이때 구현한 .getById()메서드는 JpaRepository클래스의 메서드
        return productEntity;
    }
}
