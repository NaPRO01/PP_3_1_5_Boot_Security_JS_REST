package ru.kata.spring.boot_security.demo.configs.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.configs.configs.models.Role;
import ru.kata.spring.boot_security.demo.configs.configs.models.User;
import ru.kata.spring.boot_security.demo.configs.configs.services.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.configs.configs.services.UserServiceImpl;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UsersAndRoles implements CommandLineRunner {
    private RoleServiceImpl roleServiceImpl;
    private UserServiceImpl userServiceImpl;

    @Autowired
    public UsersAndRoles(RoleServiceImpl roleServiceImpl, UserServiceImpl userServiceImpl) {
        this.roleServiceImpl = roleServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public void run(String... args) {
        Role userTest = new Role(1L, "ROLE_USER");
        Role adminTest = new Role(2L, "ROLE_ADMIN");
        roleServiceImpl.saveRole(userTest);
        roleServiceImpl.saveRole(adminTest);
        Set<Role> userSet = Stream.of(userTest).collect(Collectors.toSet());
        Set<Role> adminSet = Stream.of(adminTest).collect(Collectors.toSet());

        User user = new User("User", "User", "user", userSet);
        User admin = new User("Admin", "Admin", "admin", adminSet);
        userServiceImpl.add(user);
        userServiceImpl.add(admin);

    }
}
