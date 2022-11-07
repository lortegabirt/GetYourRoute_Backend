package birt.eus.getyourroutebackend.repository;

import birt.eus.getyourroutebackend.model.Itinerary;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItineraryRepositoryCustomImpl implements ItineraryRepositoryCustom {

  private final MongoTemplate mongoTemplate;

  @Override
  public Page<Itinerary> findFiltered(Query query, Pageable pageable) {
    return PageableExecutionUtils.getPage(
      mongoTemplate.find(query.with(pageable), Itinerary.class),
      pageable,
      () -> mongoTemplate.count(query.skip(0).limit(0), Itinerary.class));
  }
}
