package birt.eus.getyourroutebackend.model.dto;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import lombok.Setter;

@Setter
public class UserQueryParams {
  private String name;
  private String lastName;
  private String email;
  
  public Query getQuery() {
    Criteria criteria = new Criteria();

    if (name != null) {
      criteria.and("name").regex(name, "i");
    }
    if (lastName != null) {
        criteria.and("lastName").regex(lastName, "i");
    }
    if (email != null) {
        criteria.and("email").is(email);
    }    
    return new Query().addCriteria(criteria);
  }
}