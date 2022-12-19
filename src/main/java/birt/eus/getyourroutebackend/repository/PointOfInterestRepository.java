package birt.eus.getyourroutebackend.repository;

import java.util.List;

import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import birt.eus.getyourroutebackend.model.FeatureType;
import birt.eus.getyourroutebackend.model.PointOfInterest;

@Repository
public interface PointOfInterestRepository
		extends MongoRepository<PointOfInterest, String>, PointOfInterestRepositoryCustom {
	List<PointOfInterest> findByLocationNear(GeoJsonPoint point, Distance distance);
	
	List<PointOfInterest> findByLocationWithin(Circle circle);
	
	List<PointOfInterest> findByLocationWithin(Box box);

	List<PointOfInterest> findByType(FeatureType type);
}
