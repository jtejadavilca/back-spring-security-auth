package com.portfolio.jtvdev.springsecurity.application.port.out;

import com.portfolio.jtvdev.springsecurity.infrastructure.db.entity.UserEntity;
import java.util.Optional;

public interface UserPort {
  boolean existsByUsername(String username);

  Optional<UserEntity> findByUsername(String username);

  UserEntity save(UserEntity userEntity);
}
