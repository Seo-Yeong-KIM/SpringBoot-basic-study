package com.example.testprojcet.controller;

import com.example.testprojcet.common.Constants;
import com.example.testprojcet.common.exception.CustomException;
import com.example.testprojcet.data.dto.ProductDTO;
import com.example.testprojcet.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product-api")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // http://localhost:8080/api/v1/product-api/product/{productId}
    @GetMapping(value = "/product/{productId}")
    public ProductDTO getProduct(@PathVariable String productId) {

        ProductDTO productDto = productService.getProduct(productId);

        return productDto;
    }

    @PostMapping(value = "/product")
    public ProductDTO createProduct(@Valid @RequestBody ProductDTO productDto) {

        String productId = productDto.getProductId();
        String productName = productDto.getProductName();
        int productPrice = productDto.getProductPrice();
        int productStock = productDto.getProductStock();

        return productService.saveProduct(productId, productName, productPrice, productStock);
    }

    @DeleteMapping(value = "/product/{productId}")
    public ProductDTO deleteProduct(@PathVariable String productId) {
        return null;
    }

    // 예외 처리 테스트 메서드
    @PostMapping(value="/product/exception")
    public void esceptionTest() throws CustomException {
        throw new CustomException(Constants.ExceptionClass.PRODUCT, HttpStatus.BAD_REQUEST, "임의의 에러 발생");
    }

}
