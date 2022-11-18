package com.example.testprojcet.data.entity;

import com.example.testprojcet.data.dto.ProductDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "product") //테이블 명
public class ProductEntity extends BaseEntity{

    @Id // primary key 지정
    String productId;

    String productName;
    Integer productPrice;
    Integer productStock;

    // ProductDTO 객체 하나를 필드값으로 가짐
    public ProductDTO toDto() {
        return ProductDTO.builder()
            .productId(productId)
            .productName(productName)
            .productPrice(productPrice)
            .productStock(productStock)
            .build();
    }

}
