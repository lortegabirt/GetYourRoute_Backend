package birt.eus.getyourroutebackend.service;

import birt.eus.getyourroutebackend.model.User;
import birt.eus.getyourroutebackend.model.dto.UserCredentials;

public interface AuthenticationService {
  void registerUser(User user);
  String login(UserCredentials userCredentials);
}
