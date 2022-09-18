package com.portfolio.jtvdev.springsecurity.adapter.in.model;

import com.portfolio.jtvdev.springsecurity.adapter.in.dto.UserDto;
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
