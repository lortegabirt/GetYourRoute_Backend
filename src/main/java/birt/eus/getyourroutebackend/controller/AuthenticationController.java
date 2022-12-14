package birt.eus.getyourroutebackend.controller;

import birt.eus.getyourroutebackend.model.User;
import birt.eus.getyourroutebackend.model.dto.LoginResponse;
import birt.eus.getyourroutebackend.model.dto.UserCredentials;
import birt.eus.getyourroutebackend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v0/authentication")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/signup")
  @ResponseStatus(HttpStatus.CREATED)
  public void singUp(@RequestBody @Valid User user) {
    authenticationService.registerUser(user);
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody UserCredentials userCredentials) {
    return ResponseEntity.ok(authenticationService.login(userCredentials));
  }

}
