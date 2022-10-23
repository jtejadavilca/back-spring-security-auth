package com.portfolio.jtvdev.springsecurity.adapter.out.adapter;

import com.portfolio.jtvdev.springsecurity.application.config.auth.MainUser;
import com.portfolio.jtvdev.springsecurity.domain.port.out.UserDetailsPort;
import com.portfolio.jtvdev.springsecurity.domain.port.out.UserPort;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsAdapter implements UserDetailsPort {
  private final UserPort userPort;

  @Autowired
  public UserDetailsAdapter(UserPort userPort) {
    log.info("UserDetailsAdapter << ENTER");
    this.userPort = userPort;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("loadUserByUsername << ENTER");
    return userPort.findByUsername(username)
            .map(MainUser::build)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }
}
