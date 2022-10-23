package com.portfolio.jtvdev.springsecurity.infrastructure.conf;

import com.portfolio.jtvdev.springsecurity.infrastructure.conf.jwt.JwtEntryPoint;
import com.portfolio.jtvdev.springsecurity.infrastructure.conf.jwt.JwtTokenFilter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

  @Autowired
  private JwtEntryPoint jwtEntryPoint;

  @Bean
  public JwtTokenFilter jwtTokenFilter() {
    log.info("JwtTokenFilter << ENTER");
    return new JwtTokenFilter();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    log.info("passwordEncoder << ENTER");
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    log.info("filterChain << ENTER");
    http.cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/auth/**")
            .permitAll()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
