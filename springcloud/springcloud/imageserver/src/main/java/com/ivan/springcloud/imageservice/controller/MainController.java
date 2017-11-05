package com.ivan.springcloud.imageservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping(value = "/images/{userId}")
    public List<String> listImagesByUserId() {

        return Arrays.asList("http://images/1.png", "http://images/2.png", "http://images/png");
    }
}
