package com.example.testprojcet.controller;

import com.example.testprojcet.dto.MemberDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value="/api/v1/put-api")
public class PutController {
    @PutMapping(value="/default")
    public String putMethod() {
        return "putMethod";
    }

    @PutMapping(value="/member")
    public String putMember(@RequestBody Map<String, Object> map) {

        StringBuilder sb = new StringBuilder();
        map.entrySet().forEach(m -> {
            sb.append(m.getKey() + " : " + m.getValue() + "\n");
        });
        return sb.toString();
    }

    // response값 String
    @PutMapping(value="/member2")
    public String putDto1(@RequestBody MemberDTO dto) {
        return dto.toString();
    }

    // response값 DTO
    @PutMapping(value="/member3")
    public MemberDTO putDto2(@RequestBody MemberDTO dto) {
        return dto; // json 타입으로 전달됨
    }

    // response값 ResponseEntity
    @PutMapping(value="/member4")
    public ResponseEntity<MemberDTO> putDto3(@RequestBody MemberDTO dto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
        // json 타입으로 전달됨
        // status를 ACCEPTED로 설정하면, 202코드로 반환 (기본 반환 코드 200)
    }
}
