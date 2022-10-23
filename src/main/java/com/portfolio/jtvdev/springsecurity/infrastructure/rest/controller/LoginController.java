package com.portfolio.jtvdev.springsecurity.infrastructure.rest.controller;

import com.portfolio.jtvdev.springsecurity.infrastructure.rest.request.LoginRequest;
import com.portfolio.jtvdev.springsecurity.infrastructure.rest.response.LoginResponse;
import com.portfolio.jtvdev.springsecurity.application.port.in.LoginUseCase;
import com.portfolio.jtvdev.springsecurity.domain.model.LoginModel;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

      LoginModel loginEntity = LoginModel.builder()
              .username(request.getUsername())
              .password(request.getPassword())
              .build();

      return ResponseEntity.ok(loginUseCase.login(loginEntity));
    } catch (Exception e) {
      log.error("LoginController.login << ERROR: {}", e.getMessage());
      log.error("LoginController.login << ERROR:", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
