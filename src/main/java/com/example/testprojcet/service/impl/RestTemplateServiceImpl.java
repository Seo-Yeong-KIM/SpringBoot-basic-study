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
                .expand("test-proj") // ????????? ?????? ???????????? ?????? , ??? ???????????? ??????
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public ResponseEntity<MemberDTO> postDto() {

        // uri??? ???????????? ????????? ??????
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/server/member")
                .queryParam("name", "seoyeong")
                .queryParam("email", "kim.com")
                .queryParam("organ", "test-project")
                .encode()
                .build()
                .toUri();

        // dto??? ??? ??????
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("seoyeong");
        memberDTO.setEmail("kim.com");
        memberDTO.setOrgan("test-project");

        // .postForEntity(URI, ????????????, ?????? ??????)
        // dto??? ???????????? ??????????????? ???
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
                .post(uri) // post ???????????? ??????
                .header("header-name", "test-proj") // header??? ??? ?????? values ???
                .body(memberDTO);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.exchange(requestEntity, MemberDTO.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity;
    }
}