package com.portfolio.jtvdev.springsecurity.domain.port.out;

import com.portfolio.jtvdev.springsecurity.adapter.out.entity.RoleEntity;
import com.portfolio.jtvdev.springsecurity.application.shared.Roles;
import java.util.Optional;

public interface RolePort {
  Optional<RoleEntity> findByRoleName(Roles roleName);
}
