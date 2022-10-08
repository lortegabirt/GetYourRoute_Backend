package birt.eus.getyourroutebackend.controller;

import birt.eus.getyourroutebackend.model.User;
import birt.eus.getyourroutebackend.model.dto.UserCredentials;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v0/authentication")
public class AuthenticationController {

  @PostMapping("/singup")
  public String singUp(@RequestBody @Valid User user) {
    return "";
  }

  @PostMapping("/login")
  public String login(@RequestBody UserCredentials userCredentials) {
    return "";
  }

}
