package birt.eus.getyourroutebackend.service;

import birt.eus.getyourroutebackend.model.User;
import birt.eus.getyourroutebackend.model.dto.UserCredentials;
import birt.eus.getyourroutebackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  public void registerUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
  }

  public String login(UserCredentials userCredentials) {
    Optional<User> user = userRepository.findByEmail(userCredentials.email());
    return userCredentials.password();
  }

}
