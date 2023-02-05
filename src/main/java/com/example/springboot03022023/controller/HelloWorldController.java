package com.example.springboot03022023.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("/")
    public String getHelloWorld() {
        return "<h1>Hallo Spring boot Schulung!!</h1>";
    }
}
