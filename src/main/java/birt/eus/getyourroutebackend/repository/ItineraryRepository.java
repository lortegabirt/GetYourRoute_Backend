package birt.eus.getyourroutebackend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import birt.eus.getyourroutebackend.model.Itinerary;
import birt.eus.getyourroutebackend.model.User;

public interface ItineraryRepository extends MongoRepository<Itinerary, String> {

	List<Itinerary> findByName(String name);
	List<Itinerary> findByUser(User user);

}
