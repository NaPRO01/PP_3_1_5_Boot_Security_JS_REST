package ru.kata.spring.boot_security.demo.configs.configs.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.configs.configs.models.Role;
import ru.kata.spring.boot_security.demo.configs.configs.models.User;
import ru.kata.spring.boot_security.demo.configs.configs.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.configs.configs.services.UserServiceImpl;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserServiceImpl userServiceImpl;

    private RoleServiceImpl roleServiceImpl;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping("/")
    public String printUsers(Model model, Principal principal) {
        User user = userServiceImpl.findByUserName(principal.getName());
        model.addAttribute("user", user);
        List<User> listOfUsers = userServiceImpl.getAllUser();
        model.addAttribute("listOfUsers", listOfUsers);
        return "users";
    }

    @GetMapping("/addNewUser")
    public String showCreateUserForm(Model model) {
        User user = new User();
        List<Role> roles = roleServiceImpl.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "new_user_info";
    }

    @PostMapping("/")
    public String addUser(@ModelAttribute("user") User user) {
        userServiceImpl.add(user);
        return "redirect:/admin/";
    }

    @GetMapping("/{id}/edit")
    public String showEditUserForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userServiceImpl.show(id));
        return "/edit_user";
    }

    @PatchMapping("/{id}")
    public String saveEditUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userServiceImpl.update(user);
        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.delete(id);
        return "redirect:/admin/";
    }

}
