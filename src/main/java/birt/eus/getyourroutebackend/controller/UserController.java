package birt.eus.getyourroutebackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import birt.eus.getyourroutebackend.model.User;
import birt.eus.getyourroutebackend.repository.UserRepository;


@RestController
@RequestMapping ("api/users")
public class UserController  {

	@Autowired
	UserRepository userRepository;
	
	@GetMapping({"/",""})
	public List <User> index() {
		return userRepository.findAll();
	}
	
}
