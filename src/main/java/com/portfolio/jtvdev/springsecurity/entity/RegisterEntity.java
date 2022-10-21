package com.portfolio.jtvdev.springsecurity.entity;

import java.util.Set;

import com.portfolio.jtvdev.springsecurity.adapter.out.entity.RoleEntity;
import com.portfolio.jtvdev.springsecurity.adapter.out.entity.UserEntity;
import com.portfolio.jtvdev.springsecurity.application.shared.Roles;
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

    public UserEntity toUserEntity(String encodedPassword) {
        return UserEntity.builder()
            .firstName(firstName)
            .lastName(lastName)
            .email(username)
            .password(encodedPassword)
            .build();
    }
}
