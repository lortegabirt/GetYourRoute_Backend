package birt.eus.getyourroutebackend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import birt.eus.getyourroutebackend.model.GeoLocation;

public interface GeoLocationRepository extends MongoRepository<GeoLocation, String> {
	
	//@Aggregation(pipeline = { " { '$match': { '_id': { '$in':  ?0  } } }" } )
	@Query(value= "{itineraryId: ?0}", fields="{id:1}") 
	List<String> findByGeoLocationsIdItinerary(String itineraryId);
	
}
