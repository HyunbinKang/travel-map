package com.my.myProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyTravelController {

    @GetMapping("/")
    public String index() {
        return "index"; // 템플릿에 맞춰서 .html 혹은 .jsp로
    }

    @RequestMapping(value="/travelMap")
    public String travelMap() {
        return "travel-map";
    }
}