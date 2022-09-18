package com.portfolio.jtvdev.springsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterEntity {
  private String firstName;
  private String lastName;
  private String username;
  private String password;

}
