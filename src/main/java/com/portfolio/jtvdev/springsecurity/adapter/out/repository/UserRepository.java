package com.portfolio.jtvdev.springsecurity.adapter.out.repository;

import com.portfolio.jtvdev.springsecurity.adapter.out.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
  Optional<UserEntity> findByEmail(String email);
  boolean existsByEmail(String email);
}
