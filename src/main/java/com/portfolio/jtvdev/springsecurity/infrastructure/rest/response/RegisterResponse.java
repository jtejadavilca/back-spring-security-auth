package com.portfolio.jtvdev.springsecurity.infrastructure.rest.response;

import com.portfolio.jtvdev.springsecurity.infrastructure.rest.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class RegisterResponse extends Response<UserDto> {
  private static final long serialVersionUID = 1L;
  public RegisterResponse() {
    super();
  }
}
