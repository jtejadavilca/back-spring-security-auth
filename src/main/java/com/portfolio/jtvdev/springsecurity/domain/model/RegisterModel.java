package com.portfolio.jtvdev.springsecurity.domain.model;

import com.portfolio.jtvdev.springsecurity.infrastructure.db.entity.UserEntity;
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
public class RegisterModel {
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
