package birt.eus.getyourroutebackend.service;

import birt.eus.getyourroutebackend.model.User;
import birt.eus.getyourroutebackend.model.dto.LoginResponse;
import birt.eus.getyourroutebackend.model.dto.UserCredentials;
import birt.eus.getyourroutebackend.repository.UserRepository;
import birt.eus.getyourroutebackend.security.CustomUserDetails;
import birt.eus.getyourroutebackend.security.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationService implements AuthenticationService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenService jwtTokenService;

  @Override
  public void registerUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
  }

  @Override
  public LoginResponse login(UserCredentials userCredentials) {
    var authToken = new UsernamePasswordAuthenticationToken(userCredentials.email(), userCredentials.password());
    var principal =(CustomUserDetails) authenticationManager.authenticate(authToken).getPrincipal();
    String jwt = jwtTokenService.generateToken(userCredentials.email(), principal.getUsername(), principal.getId());
    return new LoginResponse(jwt);
  }

}
