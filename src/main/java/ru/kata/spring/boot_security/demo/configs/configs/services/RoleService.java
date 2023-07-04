package ru.kata.spring.boot_security.demo.configs.configs.services;

import ru.kata.spring.boot_security.demo.configs.configs.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    void saveRole(Role role);
}
