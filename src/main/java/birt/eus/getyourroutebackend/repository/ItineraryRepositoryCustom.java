package birt.eus.getyourroutebackend.repository;

import birt.eus.getyourroutebackend.model.Itinerary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface ItineraryRepositoryCustom {
  Page<Itinerary> findFiltered(Query query, Pageable pageable);
}
