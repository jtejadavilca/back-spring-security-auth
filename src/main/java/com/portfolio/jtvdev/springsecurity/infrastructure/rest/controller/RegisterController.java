package com.portfolio.jtvdev.springsecurity.infrastructure.rest.controller;

import com.portfolio.jtvdev.springsecurity.infrastructure.rest.request.RegisterRequest;
import com.portfolio.jtvdev.springsecurity.infrastructure.rest.response.RegisterResponse;
import com.portfolio.jtvdev.springsecurity.application.port.in.RegisterUseCase;
import com.portfolio.jtvdev.springsecurity.domain.model.RegisterModel;
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
@RequestMapping("/api/auth/register")
public class RegisterController {

  private final RegisterUseCase registerUseCase;

  @Autowired
  public RegisterController(RegisterUseCase registerUseCase) {
    this.registerUseCase = registerUseCase;
  }

  @PostMapping
  public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request, BindingResult bindingResult) {
    if(bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().build();
    }

    try{
      RegisterModel registerEntity = RegisterModel.builder()
              .firstName(request.getFirstName())
              .lastName(request.getLastName())
              .username(request.getEmail())
              .password(request.getPassword())
              .build();



      return ResponseEntity.ok(registerUseCase.register(registerEntity));
    } catch (Exception e) {
      log.error("Error in register: {}", e.getMessage());
      log.error("Error in register:", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
