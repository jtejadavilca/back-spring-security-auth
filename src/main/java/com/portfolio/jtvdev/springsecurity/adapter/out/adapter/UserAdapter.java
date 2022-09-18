package com.portfolio.jtvdev.springsecurity.adapter.out.adapter;

import com.portfolio.jtvdev.springsecurity.adapter.out.entity.UserEntity;
import com.portfolio.jtvdev.springsecurity.adapter.out.repository.UserRepository;
import com.portfolio.jtvdev.springsecurity.domain.port.out.UserPort;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserAdapter implements UserPort {

  private final UserRepository userRepository;

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
