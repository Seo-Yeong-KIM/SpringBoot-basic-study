package com.example.testprojcet.data.dto;

import com.example.testprojcet.data.entity.ProductEntity;
import lombok.*;

@Data
@NoArgsConstructor // 파라미터 없는 생성자 생성
@AllArgsConstructor // 모든 필드값을 파라미터로 갖는 생성자 생성
@ToString
@Builder // 생성자를 토대로 객체 생성
public class ProductDTO {

    private String productId;
    private String productName;
    private int productPrice;
    private int productStock;

    // ProductEntity 객체 하나를 필드값으로 가짐
    public ProductEntity toEntity() {
        return ProductEntity.builder()
            .productId(productId)
            .productName(productName)
            .productPrice(productPrice)
            .productStock(productStock)
            .build();
    }

}
