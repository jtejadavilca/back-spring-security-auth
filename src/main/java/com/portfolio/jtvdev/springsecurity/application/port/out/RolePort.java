package com.portfolio.jtvdev.springsecurity.application.port.out;

import com.portfolio.jtvdev.springsecurity.infrastructure.db.entity.RoleEntity;
import com.portfolio.jtvdev.springsecurity.application.shared.Roles;

import java.util.List;
import java.util.Optional;

public interface RolePort {
  Optional<RoleEntity> findByRoleName(Roles roleName);

  List<RoleEntity> findAll();

  RoleEntity save(RoleEntity role);
}
