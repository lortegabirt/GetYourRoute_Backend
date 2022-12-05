package birt.eus.getyourroutebackend.repository;

import birt.eus.getyourroutebackend.model.FeatureType;
import birt.eus.getyourroutebackend.model.PointOfInterest;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointOfInterestRepository
		extends MongoRepository<PointOfInterest, String>, PointOfInterestRepositoryCustom {
	List<PointOfInterest> findByLocationNear(GeoJsonPoint point, Distance distance);

	List<PointOfInterest> findByLocationWithin(Box box);

	List<PointOfInterest> findByType(FeatureType type);
}
