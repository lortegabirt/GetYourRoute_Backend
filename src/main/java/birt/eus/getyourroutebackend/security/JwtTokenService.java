package birt.eus.getyourroutebackend.security;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtTokenService {

  private final Key key;

  public JwtTokenService(@Value("${eus.birt.jwt.key}")String secret) {
    key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  public String generateToken(String email) {
    return Jwts.builder()
      .setSubject(email)
      .signWith(key)
      .setIssuedAt(new Date())
      .compact();
  }

  public Jwt verify(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build().parse(token);
  }
}
