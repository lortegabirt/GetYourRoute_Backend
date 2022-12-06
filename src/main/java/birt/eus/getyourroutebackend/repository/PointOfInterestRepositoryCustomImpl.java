package birt.eus.getyourroutebackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import birt.eus.getyourroutebackend.model.PointOfInterest;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PointOfInterestRepositoryCustomImpl implements PointOfInterestRepositoryCustom {

	private final MongoTemplate mongoTemplate;

	@Override
	public Page<PointOfInterest> findFiltered(Query query, Pageable pageable) {
		return PageableExecutionUtils.getPage(mongoTemplate.find(query.with(pageable), PointOfInterest.class), pageable,
				() -> mongoTemplate.count(query.skip(0).limit(0), PointOfInterest.class));
	}
}