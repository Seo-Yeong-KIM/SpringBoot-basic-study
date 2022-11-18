package com.example.testprojcet.service.impl;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;

import com.example.testprojcet.data.dao.ShortUrlDAO;
import com.example.testprojcet.data.dto.NaverUriDto;
import com.example.testprojcet.data.dto.ShortUrlResponseDto;
import com.example.testprojcet.data.entity.ShortUrl;
import com.example.testprojcet.data.repository.ShortUrlRedisRepository;
import com.example.testprojcet.service.ShortUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    private final Logger LOGGER = LoggerFactory.getLogger(ShortUrlServiceImpl.class);

    private final ShortUrlDAO shortUrlDAO;
    private final ShortUrlRedisRepository shortUrlRedisRepository; // Redis 사용을 위한 repository

    @Autowired
    public ShortUrlServiceImpl(ShortUrlDAO shortUrlDAO, ShortUrlRedisRepository shortUrlRedisRepository) {
        this.shortUrlDAO = shortUrlDAO;
        this.shortUrlRedisRepository = shortUrlRedisRepository;
    }

    // 1. URL 생성
    @Override
    public ShortUrlResponseDto generateShortUrl(String clientId, String clientSecret,
                                                String originalUrl) {

        LOGGER.info("[generateShortUrl] request data : {}", originalUrl);

//        if(originalUrl.contains("me2.do")){
//            throw new RuntimeException();
//        }

        // API 연동 메서드
        ResponseEntity<NaverUriDto> responseEntity = requestShortUrl(clientId, clientSecret,
                originalUrl);

        String orgUrl = responseEntity.getBody().getResult().getOrgUrl();
        String shortUrl = responseEntity.getBody().getResult().getUrl();
        String hash = responseEntity.getBody().getResult().getHash();

        // DAO에는 Entity로 전달
        ShortUrl shortUrlEntity = new ShortUrl();
        shortUrlEntity.setOrgUrl(orgUrl);
        shortUrlEntity.setUrl(shortUrl);
        shortUrlEntity.setHash(hash);

        shortUrlDAO.saveShortUrl(shortUrlEntity);

        // 컨트롤러에는 DTO로 전달
        ShortUrlResponseDto shortUrlResponseDto = new ShortUrlResponseDto(orgUrl, shortUrl);

        // 캐시 사용. 데이터를 redis리포지토리에 저장
        shortUrlRedisRepository.save(shortUrlResponseDto);

        LOGGER.info("[generateShortUrl] Response DTO : {}", shortUrlResponseDto.toString());
        return shortUrlResponseDto;
    }

    // 2. URL 조회
    @Override
    public ShortUrlResponseDto getShortUrl(String clientId, String clientSecret,
                                           String originalUrl) {

        // 파라미터로 받은 Url을 출력함 (단축화되기 전인 원본 url)
        LOGGER.info("[getShortUrl] request data : {}", originalUrl);

        // 캐시 사용. DB에 접근하기 전 해당 데이터가 있는지 확인
        Optional<ShortUrlResponseDto> foundResponseDto = shortUrlRedisRepository.findById(originalUrl);
        if (foundResponseDto.isPresent()) {
            LOGGER.info("[getShortUrl] Cache Data existed.");
            return foundResponseDto.get();
        } else {
            LOGGER.info("[getShortUrl] Cache Data does not existed.");
        }

        ShortUrl getShortUrlEntity = shortUrlDAO.getShortUrl(originalUrl);

        // 원본 url 과 단축 url
        String orgUrl;
        String shortUrl;

        if (getShortUrlEntity == null) { // 단축 url이 없을 경우
            LOGGER.info("[getShortUrl] No Entity in Database.");
            ResponseEntity<NaverUriDto> responseEntity = requestShortUrl(clientId, clientSecret,
                    originalUrl); // 단축 url을 만드는 작업도 수행

            orgUrl = responseEntity.getBody().getResult().getOrgUrl();
            shortUrl = responseEntity.getBody().getResult().getUrl();

        } else { // 단축 url이 있을 경우
            orgUrl = getShortUrlEntity.getOrgUrl();
            shortUrl = getShortUrlEntity.getUrl();
        }

        ShortUrlResponseDto shortUrlResponseDto = new ShortUrlResponseDto(orgUrl, shortUrl);

        LOGGER.info("[getShortUrl] Response DTO : {}", shortUrlResponseDto.toString());
        return shortUrlResponseDto;
    }

    // 3. URL 수정
    @Override
    public ShortUrlResponseDto updateShortUrl(String clientId, String clientSecret,
                                              String originalUrl) {
        return null;
    }

    // 4. URL 삭제
    @Override
    public void deleteShortUrl(String url) {
        if(url.contains("me2.do")){ // 파라미터로 들어온 값이 단축 url일 경우
            LOGGER.info("[deleteShortUrl] Request Url is 'ShortUrl'.");
            deleteByShortUrl(url);
        }else{ // 원본 url일 경우
            LOGGER.info("[deleteShortUrl] Request Url is 'OriginalUrl'.");
            deleteByOriginalUrl(url);
        }
    }

    // 단축 / 원본 url 로 삭제하는 메서드
    private void deleteByShortUrl(String url){
        LOGGER.info("[deleteByShortUrl] delete record");
        shortUrlDAO.deleteByShortUrl(url);
    }
    private void deleteByOriginalUrl(String url){
        LOGGER.info("[deleteByOriginalUrl] delete record");
        shortUrlDAO.deleteByOriginalUrl(url);
    }

    // API 연동 메서드
    private ResponseEntity<NaverUriDto> requestShortUrl(String clientId, String clientSecret,
                                                        String originalUrl) {
        // 받아온 원본 url을 로그에 출력
        LOGGER.info("[requestShortUrl] client ID : ***, client Secret : ***, original URL : {}", originalUrl);

        // uri 생성
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/util/shorturl")
                .queryParam("url", originalUrl)
                .encode()
                .build()
                .toUri();

        // HTTP 헤더 생성
        LOGGER.info("[requestShortUrl] set HTTP Request Header");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        // body와 header를 연결
        HttpEntity<String> entity = new HttpEntity<>("", headers);

        // RestTemplate를 사용
        RestTemplate restTemplate = new RestTemplate();
        LOGGER.info("[requestShortUrl] request by restTemplate");
        ResponseEntity<NaverUriDto> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,
                entity, NaverUriDto.class);

        LOGGER.info("[requestShortUrl] request has been successfully complete.");

        return responseEntity;
    }

}