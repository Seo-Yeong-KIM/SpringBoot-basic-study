package com.example.testprojcet.controller;

import com.example.testprojcet.dto.MemberDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value="/api/v1/delete-api")
public class DeleteController {

    // 삭제 시 필요한 매개변수 받아야함.
    @DeleteMapping(value="/delete/{variable}")
    public String DeleteVariable(@PathVariable String variable) {
        return variable;
    }

}
