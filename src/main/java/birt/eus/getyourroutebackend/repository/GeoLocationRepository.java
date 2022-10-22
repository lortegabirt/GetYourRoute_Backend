package birt.eus.getyourroutebackend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import birt.eus.getyourroutebackend.model.GeoLocation;

public interface GeoLocationRepository extends MongoRepository<GeoLocation, String> {
	
	@Query(value= "{itineraryId: ?0}", fields="{id:{ $toString: $_id }}")
	List<String> findByGeoLocationsIdItinerary(String itineraryId);
	
}
