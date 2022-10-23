package com.portfolio.jtvdev.springsecurity.domain.business;

import com.portfolio.jtvdev.springsecurity.infrastructure.rest.response.RegisterResponse;
import com.portfolio.jtvdev.springsecurity.infrastructure.db.entity.UserEntity;
import com.portfolio.jtvdev.springsecurity.infrastructure.db.repository.RoleRepository;
import com.portfolio.jtvdev.springsecurity.infrastructure.db.repository.UserRepository;
import com.portfolio.jtvdev.springsecurity.application.shared.Roles;
import com.portfolio.jtvdev.springsecurity.application.port.in.RegisterUseCase;
import com.portfolio.jtvdev.springsecurity.domain.model.RegisterModel;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class RegisterBusiness implements RegisterUseCase {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public RegisterBusiness(
          UserRepository userRepository,
          RoleRepository roleRepository,
          PasswordEncoder passwordEncoder) {
    log.info("RegisterBusiness << ENTER");
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }
  @Override
  public RegisterResponse register(RegisterModel registerEntity) throws Exception {
    log.info("register << ENTER");
//    var userEntity = UserEntity.builder()
//            .firstName(registerEntity.getFirstName())
//            .lastName(registerEntity.getLastName())
//            .email(registerEntity.getUsername())
//            .password(passwordEncoder.encode(registerEntity.getPassword()))
//            .build();
    try{
      UserEntity userEntity = registerEntity.toUserEntity(passwordEncoder.encode(registerEntity.getPassword()));

      roleRepository.findByRoleName(Roles.ROLE_USER).ifPresent(userEntity::addRole);

      log.info("userEntity.getPassword(): {}", userEntity.getPassword());

      var resp = this.userRepository.save(userEntity).toRegisterResponse();
      return resp;

    } catch (Exception e) {
      log.error("Error in register: {}", e.getMessage());
      log.error("Error in register:", e);
      throw new Exception("Error in register");
    }
  }
}
