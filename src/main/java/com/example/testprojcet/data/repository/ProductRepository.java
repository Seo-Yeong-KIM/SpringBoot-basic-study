package com.example.testprojcet.data.repository;

import com.example.testprojcet.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// JpaRepository를 상속받아 기본적으로 가진 CRUD메서드 사용 가능
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    // 쿼리 메소드 작성

    // 조회
    List<ProductEntity> findByProductName(String productName); // findBy엔티티명()
    List<ProductEntity> queryByProductName(String productName);

    // 존재 여부
    boolean existsByProductName(String productName);

    // 수행된 쿼리의 갯수
    long countByProductName(String productName);

    // 삭제
    void deleteByProductName(String productName);
    long removeByProductName(String productName);

    // 특정 갯수만 가져오기
    List<ProductEntity> findFirst5ByProductName(String productName); // findFirst데이터개수ByName
    List<ProductEntity> findTop3ByProductName(String productName);

    
    // 조건자 쿼리 메서드 작성

    // Is, Equals
    ProductEntity findByProductIdIs(String productId);
    ProductEntity findByProductIdEquals(String productId);

    // (Is)Not
    List<ProductEntity> findByProductIdNot(String productId);
    List<ProductEntity> findByProductIdIsNot(String productId);

    // (Is)Null, (Is)NotNull
    List<ProductEntity> findByProductStockIsNull();
    List<ProductEntity> findByProductStockIsNotNull();

    // And, Or
    List<ProductEntity> findTopByProductIdAndProductName(String productId, String productName);

    // (Is)GreaterThan, (Is)LessThan, (Is)Between
    // 해당 값보다 큰 데이터
    List<ProductEntity> findByProductPriceGreaterThan(Integer productPrice);

    // (Is)Like, (Is)Containing, (Is)StartingWith, (Is)EndingWith
    List<ProductEntity> findByProductNameContaining(String productName);


    // 정렬 & 페이징

    // Asc 오름차순, Desc 내림차순
    List<ProductEntity> findByProductNameContainingOrderByProductStockAsc(String productName); // 이름을 포함하는 데이터를 추출해서 오름차순으로 정렬
    List<ProductEntity> findByProductNameContainingOrderByProductStockDesc(String productName);

    // Price는 오름차순으로, Stock은 내림차순으로 정렬
    List<ProductEntity> findByProductNameContainingOrderByProductPriceAscProductStockDesc(String productName);

    // Sort 객체를 사용해서 정렬
    // 메서드명(정렬할 데이터, Sort)
    List<ProductEntity> findByProductNameContaining(String productName, Sort sort);

    // Pageable 객체를 사용해서 페이징
    // 메서드명(페이지 인덱스, Pageable)
    List<ProductEntity> findByProductPriceGreaterThan(Integer productPrice, Pageable pageable);


    // @Query 어노테이션 사용

//    @Query("SELECT p FROM Product p WHERE p.price > 2000")
//    List<ProductEntity> findByProductPriceBasis();
//
//    @Query(value = "SELECT * FROM product p WHERE p.price > 2000", nativeQuery = true)
//    // JPA 에서는 JPQL(SQL과 비슷함)을 사용. native 쿼리를 사용하면 직접 SQL문을 사용할 수 있음
//    List<ProductEntity> findByProductPriceBasisNativeQuery();
//
//    @Query("SELECT p FROM Product p WHERE p.price > ?1") // 고정값이 아닌 파라미터를 받아옴
//    List<ProductEntity> findByPriceWithParameter(Integer productPrice);
//
//    @Query("SELECT p FROM Product p WHERE p.price > :price") // 변수명으로 파라미터를 받아옴
//    List<ProductEntity> findByPriceWithParameterNaming(Integer productPrice);
//
//    @Query("SELECT p FROM Product p WHERE p.price > :pri") // 파라미터를 @Param에 명시
//    List<ProductEntity> findByPriceWithParameterNaming2(@Param("pri") Integer productPrice);
//
//    @Query(value = "SELECT * FROM product WHERE price > :price",
//            countQuery = "SELECT count(*) FROM product WHERE price > ?1",
//            nativeQuery = true) // native 쿼리와 페이징
//    List<ProductEntity> findByPriceWithParameterPaging(Integer productPrice, Pageable pageable);

}