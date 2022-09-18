package com.portfolio.jtvdev.springsecurity.domain.business;

import com.portfolio.jtvdev.springsecurity.adapter.in.model.LoginResponse;
import com.portfolio.jtvdev.springsecurity.application.shared.TokenTypes;
import com.portfolio.jtvdev.springsecurity.domain.port.in.LoginUseCase;
import com.portfolio.jtvdev.springsecurity.domain.port.out.JwtProviderPort;
import com.portfolio.jtvdev.springsecurity.domain.port.out.RolePort;
import com.portfolio.jtvdev.springsecurity.domain.port.out.UserPort;
import com.portfolio.jtvdev.springsecurity.entity.LoginEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
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
    this.authenticationManagerBuilder = authenticationManagerBuilder;
    this.passwordEncoder = passwordEncoder;
    this.userPort = userPort;
    this.rolePort = rolePort;
    this.jwtProviderPort = jwtProviderPort;
  }

  @Override
  public LoginResponse login(LoginEntity loginEntity) throws Exception {
    var authenticationToken = new UsernamePasswordAuthenticationToken(
            loginEntity.getUsername(),
            loginEntity.getPassword()
    );
    var authentication = this.authenticationManagerBuilder.getObject().authenticate(authenticationToken);

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = this.jwtProviderPort.generateToken(authentication);
    long expiresIn = this.jwtProviderPort.getExpirationDateFromToken(jwt).getTime() - System.currentTimeMillis();

    return LoginResponse.builder()
            .accessToken(jwt)
            .expiresIn(expiresIn)
            .tokenType(TokenTypes.TOKEN_BEARER.getType())
            .build();
  }
}
