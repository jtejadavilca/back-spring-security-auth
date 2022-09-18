package com.portfolio.jtvdev.springsecurity.domain.port.out;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsPort {
  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
