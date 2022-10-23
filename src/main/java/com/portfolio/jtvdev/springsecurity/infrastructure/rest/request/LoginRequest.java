package com.portfolio.jtvdev.springsecurity.infrastructure.rest.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

  @NotEmpty
  @Email
  private String username;

  @NotEmpty
  private String password;
}
