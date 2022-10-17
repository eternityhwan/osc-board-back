package com.osckorea.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    
    @GetMapping(value = "/rest")
    public String HelloWorld() {
        return "Hello World 2022-10-12";
    }

}
