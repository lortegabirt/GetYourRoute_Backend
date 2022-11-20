package birt.eus.getyourroutebackend.model.dto;

import lombok.Setter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Setter
public class ItineraryQueryParams {
  private String userId;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime beginDate;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime endDate;
  private String name;

  public Query getQuery() {
    Criteria criteria = new Criteria();
    if (userId != null) {
      criteria.and("idUser").is(userId);
    }
    if (beginDate != null && endDate != null) {
      criteria.andOperator(new Criteria().orOperator(
        new Criteria("beginDate").gt(beginDate).lt(endDate),
        new Criteria("endDate").gt(beginDate).lt(endDate)
      ));
    }
    if (name != null) {
      criteria.and("name").regex(name, "i");
    }
    return new Query().addCriteria(criteria);
  }
}
