package com.example.chuyentrang.controller;

import com.example.chuyentrang.model.Role;
import com.example.chuyentrang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    public Role createRole(@RequestParam String roleName) {
        return roleService.createRole(roleName);
    }
}

