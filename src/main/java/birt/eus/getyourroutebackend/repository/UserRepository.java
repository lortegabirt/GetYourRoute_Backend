package birt.eus.getyourroutebackend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import birt.eus.getyourroutebackend.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	List<User> findByName(String name);

}
