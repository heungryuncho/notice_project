package com.example.notice_project.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //RestAPI용 컨트롤러 JSON데이터를 반환
public class FistApiController {
    @GetMapping("/api/hello")
    public String hello(){
        return "hello world!";
    }
}
