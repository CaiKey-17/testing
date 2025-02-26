package com.example.chuyentrang.service;

import com.example.chuyentrang.model.Role;
import com.example.chuyentrang.model.User;
import com.example.chuyentrang.repository.RoleRepository;
import com.example.chuyentrang.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;


    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return true;
        }
        return false;
    }

    public boolean registerUser(User user, String roleName) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepository.findByRoleName(roleName);
        if (role == null) {
            role = new Role(roleName);
            roleRepository.save(role);
        }


        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        return true;
    }


    public String getUserRole(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null && !user.getRoles().isEmpty()) {
            return user.getRoles().iterator().next().getRoleName();
        }
        return null;
    }

}
