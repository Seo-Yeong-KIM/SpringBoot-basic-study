package com.example.testprojcet.data.repository;

import java.util.List;
import javax.transaction.Transactional;

import com.example.testprojcet.data.entity.ProductEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    private ProductEntity getProduct(String id, int nameNumber, int price, int stock) {
        return new ProductEntity(id, "상품" + nameNumber, price, stock);
    }


    // 쿼리 메서드
    @Test
    void findTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundEntities = productRepository.findByProductName("상품4");

        for (ProductEntity product : foundEntities) {
            System.out.println(product.toString());
        }

        List<ProductEntity> queryEntities = productRepository.queryByProductName("상품4");

        for (ProductEntity product : queryEntities) {
            System.out.println(product.toString());
        }
    }

    @Test
    void existTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        System.out.println(productRepository.existsByProductName("상품4"));
        System.out.println(productRepository.existsByProductName("상품2"));
    }

    @Test
    void countTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        System.out.println(productRepository.countByProductName("상품4"));
    }

    @Test
    @Transactional // delete 테스트 할 때는 Transactional 어노테이션 붙여야 함
    void deleteTest() {
        System.out.println("before : " + productRepository.count());

        productRepository.deleteByProductName("상품1");
        productRepository.removeByProductName("상품9");

        System.out.println("After : " + productRepository.count());
    }

    @Test
    void topTest() {
        productRepository.save(getProduct("109", 123, 1500, 5000));
        productRepository.save(getProduct("101", 123, 2500, 5000));
        productRepository.save(getProduct("102", 123, 3500, 5000));
        productRepository.save(getProduct("103", 123, 4500, 5000));
        productRepository.save(getProduct("104", 123, 1000, 5000));
        productRepository.save(getProduct("105", 123, 2000, 5000));
        productRepository.save(getProduct("106", 123, 3000, 5000));
        productRepository.save(getProduct("107", 123, 4000, 5000));

        List<ProductEntity> foundEntities = productRepository.findFirst5ByProductName("상품123");
        for (ProductEntity product : foundEntities) {
            System.out.println(product.toString());
        }

        List<ProductEntity> foundEntities2 = productRepository.findTop3ByProductName("상품123");
        for (ProductEntity product : foundEntities2) {
            System.out.println(product.toString());
        }
    }


    // 조건자 쿼리 메서드
    @Test
    void isEqualsTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        System.out.println(productRepository.findByProductIdIs("1"));
        System.out.println(productRepository.findByProductIdEquals("1"));
    }

    @Test
    void notTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundEntities = productRepository.findByProductIdNot("1");
        for (ProductEntity product : foundEntities) {
            System.out.println(product);
        }
        //System.out.println(productRepository.findByProductIdNot("1"));
        System.out.println(productRepository.findByProductIdIsNot("1"));
    }

    @Test
    void nullTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        System.out.println(productRepository.findByProductStockIsNull());
        System.out.println(productRepository.findByProductStockIsNotNull());
    }

    @Test
    void andTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        System.out.println(productRepository.findTopByProductIdAndProductName("1", "상품1"));
    }

    @Test
    void greaterTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> productEntities = productRepository.findByProductPriceGreaterThan(5000);

        for (ProductEntity product : productEntities) {
            System.out.println(product);
        }
    }

    @Test
    void containTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        System.out.println(productRepository.findByProductNameContaining("상품1"));
    }


    // 정렬
    @Test
    void orderByTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByProductNameContainingOrderByProductStockAsc("상품");
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }

        foundProducts = productRepository.findByProductNameContainingOrderByProductStockDesc("상품");
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
    }

    @Test
    void multiOrderByTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByProductNameContainingOrderByProductPriceAscProductStockDesc(
                "상품");
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
    }

    @Test
    void orderByWithParameterTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        List<ProductEntity> foundProducts = productRepository.findByProductNameContaining(
                "상품", Sort.by(Order.asc("price")));
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }

        foundProducts = productRepository.findByProductNameContaining("상품",
                Sort.by(Order.asc("price"), Order.asc("stock")));
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
    }

    // 페이징
    @Test
    void pagingTest() {
        List<ProductEntity> foundAll = productRepository.findAll();
        System.out.println("====↓↓ Test Data ↓↓====");
        for (ProductEntity product : foundAll) {
            System.out.println(product.toString());
        }
        System.out.println("====↑↑ Test Data ↑↑====");

        // price가 200 이상인 데이터를 추출해서 크기가2인 페이지를 0페이지부터 출력
        List<ProductEntity> foundProducts = productRepository.findByProductPriceGreaterThan(200,
                PageRequest.of(0, 2));
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
        // price가 200 이상인 데이터를 추출해서 크기가2인 페이지를 4페이지부터 출력
        foundProducts = productRepository.findByProductPriceGreaterThan(200, PageRequest.of(4, 2));
        for (ProductEntity product : foundProducts) {
            System.out.println(product);
        }
    }


    // @Query 테스트
//    @Test
//    public void queryTest() {
//        List<ProductEntity> foundAll = productRepository.findAll();
//        System.out.println("====↓↓ Test Data ↓↓====");
//        for (ProductEntity product : foundAll) {
//            System.out.println(product.toString());
//        }
//        System.out.println("====↑↑ Test Data ↑↑====");
//
//        List<ProductEntity> foundProducts = productRepository.findByPriceBasis();
//        for (ProductEntity product : foundProducts) {
//            System.out.println(product);
//        }
//    }
//
//    @Test
//    public void nativeQueryTest() {
//        List<ProductEntity> foundAll = productRepository.findAll();
//        System.out.println("====↓↓ Test Data ↓↓====");
//        for (ProductEntity product : foundAll) {
//            System.out.println(product.toString());
//        }
//        System.out.println("====↑↑ Test Data ↑↑====");
//
//        List<ProductEntity> foundProducts = productRepository.findByProductPriceBasisNativeQuery();
//        for (ProductEntity product : foundProducts) {
//            System.out.println(product);
//        }
//    }
//
//    @Test
//    public void parameterQueryTest() {
//        List<ProductEntity> foundAll = productRepository.findAll();
//        System.out.println("====↓↓ Test Data ↓↓====");
//        for (ProductEntity product : foundAll) {
//            System.out.println(product.toString());
//        }
//        System.out.println("====↑↑ Test Data ↑↑====");
//
//        List<ProductEntity> foundProducts = productRepository.findByPriceWithParameter(2000);
//        for (ProductEntity product : foundProducts) {
//            System.out.println(product);
//        }
//    }
//
//    @Test
//    public void parameterNamingQueryTest() {
//        List<ProductEntity> foundAll = productRepository.findAll();
//        System.out.println("====↓↓ Test Data ↓↓====");
//        for (ProductEntity product : foundAll) {
//            System.out.println(product.toString());
//        }
//        System.out.println("====↑↑ Test Data ↑↑====");
//
//        List<ProductEntity> foundProducts = productRepository.findByPriceWithParameterNaming(2000);
//        for (ProductEntity product : foundProducts) {
//            System.out.println(product);
//        }
//    }
//
//    @Test
//    public void parameterNamingQueryTest2() {
//        List<ProductEntity> foundAll = productRepository.findAll();
//        System.out.println("====↓↓ Test Data ↓↓====");
//        for (ProductEntity product : foundAll) {
//            System.out.println(product.toString());
//        }
//        System.out.println("====↑↑ Test Data ↑↑====");
//
//        List<ProductEntity> foundProducts = productRepository.findByPriceWithParameterNaming2(2000);
//        for (ProductEntity product : foundProducts) {
//            System.out.println(product);
//        }
//    }
//
//    @Test
//    public void nativeQueryPagingTest() { // native 쿼리와 페이징을 사용
//        List<ProductEntity> foundAll = productRepository.findAll();
//        System.out.println("====↓↓ Test Data ↓↓====");
//        for (ProductEntity product : foundAll) {
//            System.out.println(product.toString());
//        }
//        System.out.println("====↑↑ Test Data ↑↑====");
//
//        List<ProductEntity> foundProducts = productRepository.findByPriceWithParameterPaging(2000,
//                PageRequest.of(2, 2));
//        for (ProductEntity product : foundProducts) {
//            System.out.println(product);
//        }
//    }

//    @Test
//    public void basicCRUDTest() {
//        /* create */
//        // given
//        ProductEntity product = ProductEntity.builder()
//                .productId("testProduct")
//                .productName("testP")
//                .productPrice(1000)
//                .productStock(500)
//                .build();
//
//        // when
//        ProductEntity savedEntity = productRepository.save(product);
//
//        // then
//        Assertions.assertThat(savedEntity.getProductId())
//                .isEqualTo(product.getProductId());
//        Assertions.assertThat(savedEntity.getProductName())
//                .isEqualTo(product.getProductName());
//        Assertions.assertThat(savedEntity.getProductPrice())
//                .isEqualTo(product.getProductPrice());
//        Assertions.assertThat(savedEntity.getProductStock())
//                .isEqualTo(product.getProductStock());
//
//        /* read */
//        // when
//        ProductEntity selectedEntity = productRepository.findById("testProduct")
//                .orElseThrow(RuntimeException::new);
//
//        // then
//        Assertions.assertThat(savedEntity.getProductId())
//                .isEqualTo(product.getProductId());
//        Assertions.assertThat(savedEntity.getProductName())
//                .isEqualTo(product.getProductName());
//        Assertions.assertThat(savedEntity.getProductPrice())
//                .isEqualTo(product.getProductPrice());
//        Assertions.assertThat(savedEntity.getProductStock())
//                .isEqualTo(product.getProductStock());
//    }

    // Test 실행을 위해 임의의 값을 넣어줌
    @BeforeEach
    void GenerateData() {
        int count = 1;
        productRepository.save(getProduct(Integer.toString(count), count++, 2000, 3000));
        productRepository.save(getProduct(Integer.toString(count), count++, 3000, 3000));
        productRepository.save(getProduct(Integer.toString(--count), count = count + 2, 1500, 200));
        productRepository.save(getProduct(Integer.toString(count), count++, 4000, 3000));
        productRepository.save(getProduct(Integer.toString(count), count++, 10000, 1500));
        productRepository.save(getProduct(Integer.toString(count), count++, 10000, 1000));
        productRepository.save(getProduct(Integer.toString(count), count++, 500, 10000));
        productRepository.save(getProduct(Integer.toString(count), count++, 8500, 3500));
        productRepository.save(getProduct(Integer.toString(count), count++, 1000, 2000));
        productRepository.save(getProduct(Integer.toString(count), count, 5100, 1700));
    }

}