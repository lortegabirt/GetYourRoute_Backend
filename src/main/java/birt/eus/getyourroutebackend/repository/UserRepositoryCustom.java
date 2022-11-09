package birt.eus.getyourroutebackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import birt.eus.getyourroutebackend.model.User;

public interface UserRepositoryCustom {
  Page<User> findFiltered(Query query, Pageable pageable);
}