package birt.eus.getyourroutebackend.model.dto;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Setter;

@Setter
public class GeoLocationQueryParams {
 
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime beginDate;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime endDate;  
  private String itineraryId;
  private String userId;

  
  public Query getQuery() {
    Criteria criteria = new Criteria();

    if (beginDate != null && endDate != null) {
        criteria.and("timestamp").gt(beginDate).lt(endDate);
    }
    if (itineraryId != null) {
      criteria.and("itineraryId").is(itineraryId);
    }
    if (userId != null) {
        criteria.and("userId").is(userId);
    }    
    return new Query().addCriteria(criteria);
  }
}