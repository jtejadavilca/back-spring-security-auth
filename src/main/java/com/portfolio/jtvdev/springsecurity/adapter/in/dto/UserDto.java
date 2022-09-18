package com.portfolio.jtvdev.springsecurity.adapter.in.dto;

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
public class UserDto {
  private Long id;
  private String email;
  private String password;
  private String firstName;
  private String lastName;
}
