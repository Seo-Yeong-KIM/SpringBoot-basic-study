package com.example.testprojcet.controller;

import com.example.testprojcet.dto.MemberDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value="/api/v1/get-api")
public class GetController {

    // param값 변수명 명시x
    // 예제 주소 /variable1/test
    @GetMapping(value="/variable1/{variable}")
    public String getVariable1(@PathVariable String variable) {
        return variable;
    }

    // param값 변수명 명시
    @GetMapping(value="/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var) {
        return var;
    }

    // GetParameter로 받기
    // 예제 주소 /request1?name=1&email=2&organ=3
    @GetMapping(value="/request1")
    public String getRequestParam1(@RequestParam String name, @RequestParam String email, @RequestParam String organ) {
        return name + " / " + email + " / " + organ;
    }

    // Param값 Map으로 받기
    // 예제 주소 /request2?name=1&email=2&organ=3
    @GetMapping(value="/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param) {

        StringBuilder sb = new StringBuilder();
        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });
        return sb.toString();
    }

    @GetMapping(value="/request3")
    public String getRequestParam3(MemberDTO dto) {
        return dto.toString();
    }

}
