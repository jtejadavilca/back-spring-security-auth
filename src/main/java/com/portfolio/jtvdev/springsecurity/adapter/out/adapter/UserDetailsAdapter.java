package com.portfolio.jtvdev.springsecurity.adapter.out.adapter;

import com.portfolio.jtvdev.springsecurity.application.config.auth.MainUser;
import com.portfolio.jtvdev.springsecurity.domain.port.out.UserDetailsPort;
import com.portfolio.jtvdev.springsecurity.domain.port.out.UserPort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsAdapter implements UserDetailsPort {
  private final UserPort userPort;

  public UserDetailsAdapter(UserPort userPort) {
    this.userPort = userPort;
  }

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userPort.findByUsername(username)
            .map(MainUser::build)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }
}
