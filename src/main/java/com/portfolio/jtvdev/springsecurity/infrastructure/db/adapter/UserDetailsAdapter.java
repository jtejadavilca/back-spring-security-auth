package com.portfolio.jtvdev.springsecurity.infrastructure.db.adapter;

import com.portfolio.jtvdev.springsecurity.infrastructure.conf.auth.MainUser;
import com.portfolio.jtvdev.springsecurity.application.port.out.UserDetailsPort;
import com.portfolio.jtvdev.springsecurity.application.port.out.UserPort;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
