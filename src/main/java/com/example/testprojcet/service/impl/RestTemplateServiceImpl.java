package com.example.testprojcet.service.impl;

import java.net.URI;

import com.example.testprojcet.dto.MemberDTO;
import com.example.testprojcet.service.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {

    private final Logger LOGGER = LoggerFactory.getLogger(RestTemplateServiceImpl.class);

    @Override
    public String getAroundHub() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/server/restTemplate")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public String getName() {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/server/name")
                .queryParam("name", "test-proj")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public String getName2() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/server/path-variable/{name}")
                .encode()
                .build()
                .expand("test-proj") // 복수의 값을 넣어야할 경우 , 를 추가하여 구분
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public ResponseEntity<MemberDTO> postDto() {

        // uri를 만들어서 서버로 보냄
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/server/member")
                .queryParam("name", "seoyeong")
                .queryParam("email", "kim.com")
                .queryParam("organ", "test-project")
                .encode()
                .build()
                .toUri();

        // dto에 값 세팅
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("seoyeong");
        memberDTO.setEmail("kim.com");
        memberDTO.setOrgan("test-project");

        // .postForEntity(URI, 파라미터, 반환 타입)
        // dto를 메서드의 매개변수로 씀
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.postForEntity(uri, memberDTO, MemberDTO.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity;
    }

    @Override
    public ResponseEntity<MemberDTO> addHeader() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/server/add-header")
                .encode()
                .build()
                .toUri();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("seoyeong");
        memberDTO.setEmail("kim.com");
        memberDTO.setOrgan("test-project");

        RequestEntity<MemberDTO> requestEntity = RequestEntity
                .post(uri) // post 메서드로 사용
                .header("header-name", "test-proj") // header의 키 값과 values 값
                .body(memberDTO);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.exchange(requestEntity, MemberDTO.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity;
    }
}