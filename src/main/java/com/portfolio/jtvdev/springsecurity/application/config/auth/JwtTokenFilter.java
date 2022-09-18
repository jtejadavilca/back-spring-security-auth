package com.portfolio.jtvdev.springsecurity.application.config.auth;

import com.portfolio.jtvdev.springsecurity.domain.port.out.UserDetailsPort;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

  @Autowired
  private JwtProvider jwtProvider;
  @Autowired
  private UserDetailsPort userDetailsPort;

  @Override
  protected void doFilterInternal(
          HttpServletRequest request,
          HttpServletResponse response,
          FilterChain filterChain) throws ServletException, IOException {
    try {
      String token = getToken(request);
      if (token != null && jwtProvider.validateToken(token)) {
        String username = jwtProvider.getUsernameFromToken(token);
        var userDetails = userDetailsPort.loadUserByUsername(username);
        var authentication = jwtProvider.getAuthentication(userDetails);
        log.info("User {} authenticated", username);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (Exception e) {
      log.error("Fail in set user authentication", e);
    }

    filterChain.doFilter(request, response);
  }

  private String getToken(HttpServletRequest request) {
    String header = request.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer ")) {
      return header.replace("Bearer ", "");
    }
    return null;
  }
}
