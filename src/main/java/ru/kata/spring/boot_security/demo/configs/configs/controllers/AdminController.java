package ru.kata.spring.boot_security.demo.configs.configs.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.configs.configs.models.User;
import ru.kata.spring.boot_security.demo.configs.configs.services.RoleService;
import ru.kata.spring.boot_security.demo.configs.configs.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.configs.configs.services.UserService;
import ru.kata.spring.boot_security.demo.configs.configs.services.UserServiceImpl;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String printUsers(Model model, Principal principal) {
        User user = userService.findByUserName(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("listOfUsers", userService.getAllUser());
        return "admin";
    }
}
