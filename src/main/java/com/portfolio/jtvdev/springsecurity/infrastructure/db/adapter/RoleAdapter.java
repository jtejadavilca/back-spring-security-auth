package com.portfolio.jtvdev.springsecurity.infrastructure.db.adapter;

import com.portfolio.jtvdev.springsecurity.infrastructure.db.entity.RoleEntity;
import com.portfolio.jtvdev.springsecurity.infrastructure.db.repository.RoleRepository;
import com.portfolio.jtvdev.springsecurity.application.shared.Roles;
import com.portfolio.jtvdev.springsecurity.application.port.out.RolePort;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleAdapter implements RolePort {
  private final RoleRepository roleRepository;

  @Autowired
  public RoleAdapter(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  public Optional<RoleEntity> findByRoleName(Roles roleName) {
    return roleRepository.findByRoleName(roleName);
  }

  public List<RoleEntity> findAll() {
    return roleRepository.findAll();
  }

  public RoleEntity save(RoleEntity role) {
    return roleRepository.save(role);
  }
}
