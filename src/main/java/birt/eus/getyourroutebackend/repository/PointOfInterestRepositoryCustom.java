package birt.eus.getyourroutebackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import birt.eus.getyourroutebackend.model.PointOfInterest;

public interface PointOfInterestRepositoryCustom {
	Page<PointOfInterest> findFiltered(Query query, Pageable pageable);
}