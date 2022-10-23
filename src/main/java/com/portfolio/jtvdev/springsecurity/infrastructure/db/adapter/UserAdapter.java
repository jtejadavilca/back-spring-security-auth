package com.portfolio.jtvdev.springsecurity.infrastructure.db.adapter;

import com.portfolio.jtvdev.springsecurity.infrastructure.db.entity.UserEntity;
import com.portfolio.jtvdev.springsecurity.infrastructure.db.repository.UserRepository;
import com.portfolio.jtvdev.springsecurity.application.port.out.UserPort;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserAdapter implements UserPort {

  private final UserRepository userRepository;

  @Autowired
  public UserAdapter(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<UserEntity> findByUsername(String username) {
    return userRepository.findByEmail(username);
  }

  public boolean existsByUsername(String username) {
    return userRepository.existsByEmail(username);
  }

  public UserEntity save(UserEntity userEntity) {
    return userRepository.save(userEntity);
  }

}
