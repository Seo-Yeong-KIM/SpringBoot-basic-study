package com.example.testprojcet.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JasyptTest {

    // Jasypt로 암호화한 값을 얻기 위한 테스트
    @Test
    void encryptTest(){
        String id = "test_kim";
        String password = "MARIA123#";

        System.out.println(jasyptEncoding(id));
        System.out.println(jasyptEncoding(password));
    }

    public String jasyptEncoding(String value) {
        String key = "test_project"; // 이 키 값과 jasypt config의 패스워드 값이 일치해야함
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(key);
        return pbeEnc.encrypt(value);
    }

}