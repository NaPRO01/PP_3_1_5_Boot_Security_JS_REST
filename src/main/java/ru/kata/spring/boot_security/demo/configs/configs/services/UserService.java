package ru.kata.spring.boot_security.demo.configs.configs.services;

import ru.kata.spring.boot_security.demo.configs.configs.models.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void update(User user);

    void delete(Long id);

    List<User> getAllUser();

    User show(Long id);

    User findByUserName(String name);
}
