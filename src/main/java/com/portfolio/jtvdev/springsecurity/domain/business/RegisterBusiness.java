package com.portfolio.jtvdev.springsecurity.domain.business;

import com.portfolio.jtvdev.springsecurity.adapter.in.model.RegisterResponse;
import com.portfolio.jtvdev.springsecurity.adapter.out.entity.UserEntity;
import com.portfolio.jtvdev.springsecurity.domain.port.in.RegisterUseCase;
import com.portfolio.jtvdev.springsecurity.entity.RegisterEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterBusiness implements RegisterUseCase {

  private final PasswordEncoder passwordEncoder;

  public RegisterBusiness(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }
  @Override
  public RegisterResponse register(RegisterEntity registerEntity) throws Exception {
    var userEntity = UserEntity.builder()
            .firstName(registerEntity.getFirstName())
            .lastName(registerEntity.getLastName())
            .email(registerEntity.getUsername())
            .password(passwordEncoder.encode(registerEntity.getPassword()))
            .build();

    return null;
  }
}
