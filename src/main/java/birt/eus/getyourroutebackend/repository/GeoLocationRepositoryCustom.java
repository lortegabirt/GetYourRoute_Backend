package birt.eus.getyourroutebackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import birt.eus.getyourroutebackend.model.GeoLocation;

public interface GeoLocationRepositoryCustom {
  Page<GeoLocation> findFiltered(Query query, Pageable pageable);
}