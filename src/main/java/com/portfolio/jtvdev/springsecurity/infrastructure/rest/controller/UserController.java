package com.portfolio.jtvdev.springsecurity.infrastructure.rest.controller;

import java.util.List;

import com.portfolio.jtvdev.springsecurity.infrastructure.db.entity.UserEntity;
import com.portfolio.jtvdev.springsecurity.infrastructure.db.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {
        return this.userRepository.findAll();
    }
}
