package com.gamego.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value = "/test01")
    public String test(Model model){
        //Model 객체를 이용해 데이터를 key와 value구조로 뷰에 전달
        model.addAttribute("test", "Thymeleaf Test");
        return "fragments/test"; //templates 폴더를 기준으로 위치를 반환
    }

}
