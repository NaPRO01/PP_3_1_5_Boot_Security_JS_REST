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

    private RoleService roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String printUsers(Model model, Principal principal) {
        User user = userService.findByUserName(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("listOfUsers", userService.getAllUser());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @GetMapping("/addNewUser")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @PostMapping("/")
    public String addUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin/";
    }

    @GetMapping("/{id}/edit")
    public String showEditUserForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.show(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin";
    }

    @PatchMapping("/{id}")
    public String saveEditUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.update(user);
        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/";
    }

}
