package com.example.chuyentrang;

import com.example.chuyentrang.model.Role;
import com.example.chuyentrang.model.User;
import com.example.chuyentrang.repository.RoleRepository;
import com.example.chuyentrang.repository.UserRepository;
import com.example.chuyentrang.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository, UserService userService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Cung cấp tài khoản ban đầu cho admin
        String username = "caoky.sonha@gmail.com";
        String rawPassword = "123";
        String roleName = "ROLE_ADMIN";

        Role role = roleRepository.findByRoleName(roleName);
        if (role == null) {
            role = new Role(roleName);
            roleRepository.save(role);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(rawPassword);

        if (userRepository.findByUsername(username) == null) {
            boolean isRegistered = userService.registerUser(user, roleName);
            if (isRegistered) {
                System.out.println("User " + username + " registered successfully!");
            } else {
                System.out.println("User " + username + " already exists.");
            }
        } else {
            System.out.println("User " + username + " already exists.");
        }
    }
}
