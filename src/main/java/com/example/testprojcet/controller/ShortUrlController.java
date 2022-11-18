package com.example.testprojcet.controller;

import com.example.testprojcet.data.dto.ShortUrlResponseDto;
import com.example.testprojcet.service.ShortUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/short-url")
public class ShortUrlController {

    private final Logger LOGGER = LoggerFactory.getLogger(ShortUrlController.class);

    @Value("${com.example.testprojcet.url.id}") // 경로 설정. properties에 id와 secret값 넣어둠
    private String CLIENT_ID;

    @Value("${com.example.testprojcet.url.secret}")
    private String CLIENT_SECRET;

    ShortUrlService shortUrlService;

    @Autowired
    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    // 1. URL 생성
    @PostMapping()
    public ShortUrlResponseDto generateShortUrl(String originalUrl) {
        // 네이버 API랑 연동한 id, secret 값 출력 (확인용이므로 주석처리x)
        LOGGER.info("[generateShortUrl] perform API. CLIENT_ID : {}, CLIENT_SECRET : {}", CLIENT_ID, CLIENT_SECRET);

        return shortUrlService.generateShortUrl(CLIENT_ID, CLIENT_SECRET, originalUrl);
    }

    // 2. URL 조회
    @GetMapping()
    public ShortUrlResponseDto getShortUrl(String originalUrl) {
        long startTime = System.currentTimeMillis();
        ShortUrlResponseDto shortUrlResponseDto = shortUrlService.getShortUrl(CLIENT_ID, CLIENT_SECRET, originalUrl);
        long endTime = System.currentTimeMillis();

        LOGGER.info("[getShortUrl] response Time : {}ms", (endTime - startTime));

        return shortUrlResponseDto;
    }

    // 3. URL 수정
    @PutMapping("/")
    public ShortUrlResponseDto updateShortUrl(String originalUrl) {
        return null;
    }

    // 4. URL 삭제
    @DeleteMapping("/")
    public ResponseEntity<String> deleteShortUrl(String url) {
        // 예외 처리
        try {
            shortUrlService.deleteShortUrl(url);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
    }

}