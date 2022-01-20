package com.quietjun.intellij.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    @GetMapping("/sex")
    public String hello(){
        return "Hello Spring";
    }

}
