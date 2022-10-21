package com.portfolio.jtvdev.springsecurity.adapter.in.controller;

import javax.validation.Valid;

import com.portfolio.jtvdev.springsecurity.adapter.in.model.LoginRequest;
import com.portfolio.jtvdev.springsecurity.adapter.in.model.LoginResponse;
import com.portfolio.jtvdev.springsecurity.adapter.out.adapter.RoleAdapter;
import com.portfolio.jtvdev.springsecurity.adapter.out.adapter.UserAdapter;
import com.portfolio.jtvdev.springsecurity.application.config.auth.JwtProvider;
import com.portfolio.jtvdev.springsecurity.application.shared.TokenTypes;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth/login2")
public class AuthController {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final UserAdapter userService;
    private final RoleAdapter roleService;
    private final JwtProvider jwtProvider;
    @Autowired
    public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder, PasswordEncoder passwordEncoder,
                          UserAdapter userService, RoleAdapter roleService, JwtProvider jwtProvider) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
        this.jwtProvider = jwtProvider;
    }
    @PostMapping("/login2")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest loginUser, BindingResult bidBindingResult){
        if(bidBindingResult.hasErrors())
            return ResponseEntity.badRequest().build();
        try {
            UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword());
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            var res = LoginResponse.builder()
                    .accessToken(jwt)
//            .expiresIn(expiresIn)
                    .expiresIn(3600*1000)
                    .tokenType(TokenTypes.TOKEN_BEARER.getType())
                    .build();
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
