package ru.kata.spring.boot_security.demo.configs.configs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/")
    public String getAuthenticated() {
        return "index";
    }

}
