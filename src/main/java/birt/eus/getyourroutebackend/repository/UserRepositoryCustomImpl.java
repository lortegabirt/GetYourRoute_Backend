package birt.eus.getyourroutebackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import birt.eus.getyourroutebackend.model.User;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

  private final MongoTemplate mongoTemplate;

  @Override
  public Page<User> findFiltered(Query query, Pageable pageable) {
    return PageableExecutionUtils.getPage(
      mongoTemplate.find(query.with(pageable), User.class),
      pageable,
      () -> mongoTemplate.count(query.skip(0).limit(0), User.class));
  }
}