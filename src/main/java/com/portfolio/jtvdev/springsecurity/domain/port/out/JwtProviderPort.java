package com.portfolio.jtvdev.springsecurity.domain.port.out;

import io.jsonwebtoken.Claims;
import java.util.Date;
import java.util.function.Function;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtProviderPort {

  String generateToken(Authentication authentication);

  String getUsernameFromToken(String token);

  Boolean validateToken(String token);

//  Date getExpirationDateFromToken(String token);

//  <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);


//  UsernamePasswordAuthenticationToken getAuthentication(UserDetails userDetails);
}
