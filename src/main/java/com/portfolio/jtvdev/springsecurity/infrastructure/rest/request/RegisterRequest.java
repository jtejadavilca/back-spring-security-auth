package com.portfolio.jtvdev.springsecurity.infrastructure.rest.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest implements Serializable {
  private static final long serialVersionUID = 1L;
  private Long id;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
}
