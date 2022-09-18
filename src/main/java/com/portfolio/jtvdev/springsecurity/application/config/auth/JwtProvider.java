package com.portfolio.jtvdev.springsecurity.application.config.auth;

import com.portfolio.jtvdev.springsecurity.domain.port.out.JwtProviderPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtProvider implements Serializable, JwtProviderPort {
  private static final long serialVersionUID = -2550185165626007488L;
  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private String expiration;

  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";


  // https://github.com/jwtk/jjwt#secretkey-formats
  public String generateToken(Authentication authentication) {
    SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    MainUser mainUser = (MainUser) authentication.getPrincipal();
    return Jwts.builder().setSubject(mainUser.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + Long.parseLong(expiration)))
            .signWith(key)
            .compact();
  }

  //retrieve username from jwt token
  public String getUsernameFromToken(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
  }

  //validate token
  public Boolean validateToken(String token) {
    try {
      Jwts.parserBuilder()
              .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
              .build()
              .parseClaimsJws(token);
      return true;
    } catch (MalformedJwtException ex) {
      log.error("Malformed JWT token");
    } catch (UnsupportedJwtException ex) {
      log.error("Unsupported JWT token");
    } catch (ExpiredJwtException ex) {
      log.error("Expired JWT token");
    } catch (IllegalArgumentException ex) {
      log.error("JWT claims string is empty.");
    } catch (SignatureException ex) {
      log.error("Invalid JWT signature");
    }
    return false;
  }

  //retrieve expiration date from jwt token
  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }


  public UsernamePasswordAuthenticationToken getAuthentication(UserDetails userDetails) {
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
            .build()
            .parseClaimsJws(token)
            .getBody();
  }
}
