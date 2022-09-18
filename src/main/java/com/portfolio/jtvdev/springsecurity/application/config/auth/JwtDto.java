package com.portfolio.jtvdev.springsecurity.application.config.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class JwtDto {
  private String token;
}
