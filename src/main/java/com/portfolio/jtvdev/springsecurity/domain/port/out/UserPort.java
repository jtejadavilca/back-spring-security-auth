package com.portfolio.jtvdev.springsecurity.domain.port.out;

import com.portfolio.jtvdev.springsecurity.adapter.out.entity.UserEntity;
import java.util.Optional;

public interface UserPort {
  boolean existsByUsername(String username);

  Optional<UserEntity> findByUsername(String username);

  UserEntity save(UserEntity userEntity);
}
