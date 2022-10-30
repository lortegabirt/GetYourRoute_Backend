package birt.eus.getyourroutebackend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.bind.annotation.RequestBody;

import birt.eus.getyourroutebackend.model.Itinerary;
import birt.eus.getyourroutebackend.model.User;

public interface ItineraryRepository extends MongoRepository<Itinerary, String> {

	List<Itinerary> findByName(String name);
	List<Itinerary> findByUser(User user);
	// Retorna solo los ids de los itinerarios de un usuario
	@Query(value= "{idUser:?0}", fields="{id:{ $toString: $_id }}")
	List<String> findByIdUser(String IDuser);

	// Busca los itinerarios por nombre mediante una expresion regular
	@Query(value= "{name: {$regex : ?0 }}") 
	List<Itinerary> findByNameExpr(String reg);
	
	// Busca los itinerarios entre las fechas begindate y endate
	@Query(value= "{$and:[{beginDate: ?0},{endDate: ?1}]}") 
	List<Itinerary> findByDate(LocalDateTime beginDate, LocalDateTime endDate );
		
	
	
	// Busca los itinerarios de un usuario y solo retorna sus ids
	//@Query(value= "{user:?0}", fields="{id:1}")
	//@Query(value= "{user:{users:ObjectId(?0)}}", fields="{}")
	/*Query que = new Query();
	que.addCriteria(Criteria.where("user.$id").is(new ObjectId("123456789012345678901234")));*/
	//List<String> findByIdsItinerarysUser(String userId);
}
