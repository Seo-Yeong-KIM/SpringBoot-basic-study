package com.example.testprojcet.controller;

import com.example.testprojcet.dto.MemberDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Member;
import java.util.Map;

@RestController
@RequestMapping(value="/api/v1/post-api")
public class PostController {

    @PostMapping(value="/default")
    public String postMethod() {
        return "postMethod";
    }

    // member, member2는 body에 json형식으로 값 보내야함
    @PostMapping(value="/member")
    public String postMember(@RequestBody Map<String, Object> map) {

        StringBuilder sb = new StringBuilder();
        map.entrySet().forEach(m -> {
            sb.append(m.getKey() + " : " + m.getValue() + "\n");
        });
        return sb.toString();
    }

    @PostMapping(value="/member2")
    public String postMember2(@RequestBody MemberDTO dto) {
        return dto.toString();
    }

}
