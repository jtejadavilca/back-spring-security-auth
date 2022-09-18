package com.portfolio.jtvdev.springsecurity.application.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenTypes {

  TOKEN_BEARER("Bearer"),
  TOKEN_OTP("otp");

  private final String type;
}
