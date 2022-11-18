package com.example.testprojcet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    private final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

//    @RequestMapping(value="/hello", method = RequestMethod.GET)
    @GetMapping(value="/hello")
    public String hello() {
        // 인터셉터 확인 용 로그
        System.out.println("인터셉터 확인용 로그");
        return "Hello World";
    }

    @PostMapping(value="log-test")
    public void logTest() {
        LOGGER.info("infoLog");
    }
}
