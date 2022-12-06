package birt.eus.getyourroutebackend.model.dto;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import lombok.Setter;

@Setter
public class PointOfInterestQueryParams {
  private String type;

  public Query getQuery() {
    Criteria criteria = new Criteria();
    if (type != null) {
      criteria.and("type").is(type.toUpperCase());
    }
    return new Query().addCriteria(criteria);
  }
}
