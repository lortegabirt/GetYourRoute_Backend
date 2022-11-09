package birt.eus.getyourroutebackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import birt.eus.getyourroutebackend.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {
  Optional<User> findByEmail(String email);
  boolean existsByEmail(String email);
  
  @Query(value= "{name: {$regex : ?0 }}") 
  List<User> findByName(String reg);
}
