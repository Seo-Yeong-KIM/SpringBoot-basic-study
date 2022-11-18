package com.example.testprojcet.data.repository;

import com.example.testprojcet.data.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

    // 단축url을 기준으로 레코드를 조회하는 쿼리 메서드
    ShortUrl findByUrl(String url);

    // 원본url을 기준으로 레코드를 조회하는 쿼리 메서드
    ShortUrl findByOrgUrl(String originalUrl);

}