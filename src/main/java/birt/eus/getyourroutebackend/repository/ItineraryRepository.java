package birt.eus.getyourroutebackend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import birt.eus.getyourroutebackend.model.Itinerary;
import birt.eus.getyourroutebackend.model.User;

public interface ItineraryRepository extends MongoRepository<Itinerary, String>, ItineraryRepositoryCustom  {

	List<Itinerary> findByName(String name);
	List<Itinerary> findByUser(User user);

	// Retorna solo los ids de los itinerarios de un usuario
	//@Query(value= "{idUser:?0}", fields="{id:{ $toString: $_id }}")
	//List<String> findByIdUser(String IDuser);

	// Retorna solo los ids de los itinerarios de un usuario
	@Query(value= "{idUser:?0}")
	List<Itinerary> findByIdUser(String IDuser);

}
