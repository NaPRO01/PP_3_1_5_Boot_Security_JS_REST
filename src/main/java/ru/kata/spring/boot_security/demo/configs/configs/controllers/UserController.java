package ru.kata.spring.boot_security.demo.configs.configs.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.configs.configs.models.User;
import ru.kata.spring.boot_security.demo.configs.configs.services.UserServiceImpl;

import java.security.Principal;

@Controller
public class UserController {
    private UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/user")
    public String showUser(Model model, Principal principal) {
        User user = userServiceImpl.findByUserName(principal.getName());
        model.addAttribute("user", user);
        return "user";

    }


}
