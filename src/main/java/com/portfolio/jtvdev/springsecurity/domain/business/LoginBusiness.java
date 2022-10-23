package com.portfolio.jtvdev.springsecurity.domain.business;

import com.portfolio.jtvdev.springsecurity.infrastructure.rest.response.LoginResponse;
import com.portfolio.jtvdev.springsecurity.application.shared.TokenTypes;
import com.portfolio.jtvdev.springsecurity.application.port.in.LoginUseCase;
import com.portfolio.jtvdev.springsecurity.application.port.out.JwtProviderPort;
import com.portfolio.jtvdev.springsecurity.application.port.out.RolePort;
import com.portfolio.jtvdev.springsecurity.application.port.out.UserPort;
import com.portfolio.jtvdev.springsecurity.domain.model.LoginModel;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class LoginBusiness implements LoginUseCase {
  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final PasswordEncoder passwordEncoder;
  private final UserPort userPort;
  private final RolePort rolePort;
  private final JwtProviderPort jwtProviderPort;

  @Autowired
  public LoginBusiness(
          AuthenticationManagerBuilder authenticationManagerBuilder,
          PasswordEncoder passwordEncoder,
          UserPort userPort,
          RolePort rolePort,
          JwtProviderPort jwtProviderPort
  ) {
    log.info("LoginBusiness << ENTER");
    log.info("authenticationManagerBuilder: {}", authenticationManagerBuilder);
    log.info("passwordEncoder: {}", passwordEncoder);
    log.info("userPort: {}", userPort);
    log.info("rolePort: {}", rolePort);
    log.info("jwtProviderPort: {}", jwtProviderPort);
    this.authenticationManagerBuilder = authenticationManagerBuilder;
    this.passwordEncoder = passwordEncoder;
    this.userPort = userPort;
    this.rolePort = rolePort;
    this.jwtProviderPort = jwtProviderPort;
  }

  @Override
  public LoginResponse login(LoginModel loginEntity) throws Exception {
    log.info("login << ENTER");

    try{
      var authenticationToken = new UsernamePasswordAuthenticationToken(
              loginEntity.getUsername(),
              loginEntity.getPassword()
      );

      Authentication authentication = this.authenticationManagerBuilder.getObject().authenticate(authenticationToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);

      String jwt = this.jwtProviderPort.generateToken(authentication);
      long expiresIn = this.jwtProviderPort.getExpirationDateFromToken(jwt).getTime() - System.currentTimeMillis();

      return LoginResponse.builder()
              .accessToken(jwt)
              .expiresIn(expiresIn)
              .tokenType(TokenTypes.TOKEN_BEARER.getType())
              .build();
    } catch (Exception e) {
      log.error("Error in login: {}", e.getMessage());
      log.error("Error in login:", e);
      throw new Exception("Error in login");
    }


  }
}
