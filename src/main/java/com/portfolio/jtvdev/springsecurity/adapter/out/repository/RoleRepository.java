package com.portfolio.jtvdev.springsecurity.adapter.out.repository;

import com.portfolio.jtvdev.springsecurity.adapter.out.entity.RoleEntity;
import com.portfolio.jtvdev.springsecurity.application.shared.Roles;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
  Optional<RoleEntity> findByRoleName(Roles roleName);
}
