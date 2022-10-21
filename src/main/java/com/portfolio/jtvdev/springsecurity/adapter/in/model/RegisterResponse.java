package com.portfolio.jtvdev.springsecurity.adapter.in.model;

import com.portfolio.jtvdev.springsecurity.adapter.in.dto.UserDto;
import lombok.Builder;
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
