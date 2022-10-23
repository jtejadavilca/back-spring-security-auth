package com.portfolio.jtvdev.springsecurity.infrastructure.conf.auth;

import com.portfolio.jtvdev.springsecurity.infrastructure.db.entity.UserEntity;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MainUser implements UserDetails {

  private String userName;
  private String email;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public static MainUser build(UserEntity user) {
    log.info("build << ENTER");
    List<GrantedAuthority> authorities = user
            .getRoles()
            .stream()
            .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
            .collect(Collectors.toList());
    return MainUser.builder()
            .userName(user.getFirstName())
            .email(user.getEmail())
            .password(user.getPassword())
            .authorities(authorities)
            .build();
  }

  @Override
  public String getUsername() {
    return userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
