package birt.eus.getyourroutebackend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

@Service
public class JwtTokenService {

  private final Key key;

  public JwtTokenService(@Value("${eus.birt.jwt.key}")String secret) {
    key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * Generates a signed jwt token using the provided secret key and the users email
   *
   * @param email the authentication's subject email
   * @return raw jwt token
   */
  public String generateToken(String email) {
    return Jwts.builder()
      .setSubject(email)
      .signWith(key)
      .setIssuedAt(new Date())
      .compact();
  }

  /**
   * Extracts the Authentication Bearer token from a http request
   *
   * @return raw jwt token
   */
  public String extractToken(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    if (token != null && token.startsWith("Bearer ")) {
      return token.substring(7);
    }
    return "";
  }

  /**
   * Validates a signed jwt against the local secret key and extracts the subject string
   *
   * @param token signed jwt token
   * @return the extracted Subject string from the token
   */
  public String getSubject(@NonNull String token) throws MalformedJwtException, SignatureException {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
  }

}
