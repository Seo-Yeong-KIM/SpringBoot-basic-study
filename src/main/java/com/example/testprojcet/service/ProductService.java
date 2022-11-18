package com.example.testprojcet.service;

import com.example.testprojcet.data.dto.ProductDTO;

public interface ProductService {

    ProductDTO saveProduct(String productId, String productName, int productPrice, int productStock);

    ProductDTO getProduct(String productId);

}
