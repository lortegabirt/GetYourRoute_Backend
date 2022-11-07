package birt.eus.getyourroutebackend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import birt.eus.getyourroutebackend.model.Itinerary;
import birt.eus.getyourroutebackend.model.User;

public interface ItineraryRepository extends MongoRepository<Itinerary, String>, ItineraryRepositoryCustom {

	List<Itinerary> findByName(String name);
	List<Itinerary> findByUser(User user);

	// Retorna solo los ids de los itinerarios de un usuario
	@Query(value= "{idUser:?0}", fields="{id:{ $toString: $_id }}")
	List<String> findByIdUser(String IDuser);

	// Busca los itinerarios por nombre mediante una expresion regular
	@Query(value= "{name: {$regex : ?0 }}")
	List<Itinerary> findByNameExpr(String reg);

	// Busca los itinerarios entre las fechas begindate y endate
	@Query(value= "{$and:[{beginDate:{$gte:?0}},{endDate:{$lte: ?1}}]}")
	List<Itinerary> findByBeginDateEndDate(LocalDateTime beginDate, LocalDateTime endDate);

}
