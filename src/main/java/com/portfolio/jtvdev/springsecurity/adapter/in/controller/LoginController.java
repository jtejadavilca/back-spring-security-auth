package com.portfolio.jtvdev.springsecurity.adapter.in.controller;

import com.portfolio.jtvdev.springsecurity.adapter.in.model.LoginRequest;
import com.portfolio.jtvdev.springsecurity.adapter.in.model.LoginResponse;
import com.portfolio.jtvdev.springsecurity.application.shared.TokenTypes;
import com.portfolio.jtvdev.springsecurity.domain.port.in.LoginUseCase;
import com.portfolio.jtvdev.springsecurity.domain.port.out.JwtProviderPort;
import com.portfolio.jtvdev.springsecurity.domain.port.out.RolePort;
import com.portfolio.jtvdev.springsecurity.domain.port.out.UserPort;
import com.portfolio.jtvdev.springsecurity.entity.LoginEntity;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
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
@RequestMapping("/api/auth/login")
public class LoginController {
  private final LoginUseCase loginUseCase;

  @Autowired
  public LoginController(LoginUseCase loginUseCase) {
    this.loginUseCase = loginUseCase;
  }

  @PostMapping
  public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request, BindingResult bindingResult) {
    log.info("LoginController.login << ENTER");
    if(bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().build();
    }

    try {

      LoginEntity loginEntity = LoginEntity.builder()
              .username(request.getUsername())
              .password(request.getPassword())
              .build();

      return ResponseEntity.ok(loginUseCase.login(loginEntity));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
