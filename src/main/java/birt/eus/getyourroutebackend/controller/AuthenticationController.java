package birt.eus.getyourroutebackend.controller;

import birt.eus.getyourroutebackend.model.User;
import birt.eus.getyourroutebackend.model.dto.UserCredentials;
import birt.eus.getyourroutebackend.repository.UserRepository;
import birt.eus.getyourroutebackend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v0/authentication")
public class AuthenticationController {

  private final AuthenticationService authenticationService;
  private final UserRepository userRepository;

  @PostMapping("/singup")
  @ResponseStatus(HttpStatus.CREATED)
  public void singUp(@RequestBody @Valid User user) {
    authenticationService.registerUser(user);
  }

  @PostMapping("/login")
  public String login(@RequestBody UserCredentials userCredentials) {
    return authenticationService.login(userCredentials);
  }

  @GetMapping
  public User getUser(@RequestParam String email) {
    return userRepository.findByEmail(email).orElse(null);
  }

}
