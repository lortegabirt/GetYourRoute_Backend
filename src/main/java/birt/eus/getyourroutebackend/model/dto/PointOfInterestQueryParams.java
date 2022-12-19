package birt.eus.getyourroutebackend.model.dto;

import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
// import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import lombok.Setter;

@Setter
public class PointOfInterestQueryParams {
  private String type;
  private String locNearLat;
  private String locNearLong;
  private String locNearDistance;
  
  private String bottomLeftCoorLong;
  private String bottomLeftCoorLat;
  
  private String upperRightCoorLong;
  private String upperRightCoorLat;
  
  public Query getQuery() {
    Criteria criteria = new Criteria();
    if (type != null) {
      criteria.and("type").is(type.toUpperCase());
    }
    if (locNearLat != null && locNearLong != null && locNearDistance != null) {
        Circle circle = new Circle(new Point(Double.valueOf(locNearLong), Double.valueOf(locNearLat)), 
        		        		   new Distance(Double.valueOf(locNearDistance), Metrics.KILOMETERS));
        criteria.and("location").withinSphere(circle);    	 
     }
     if (!criteria.getCriteriaObject().containsKey("location") && bottomLeftCoorLong != null && bottomLeftCoorLat != null &&
    	 upperRightCoorLong != null && upperRightCoorLat != null) {
    	 Box box = new Box(new Point(Double.valueOf(bottomLeftCoorLong), Double.valueOf(bottomLeftCoorLat)), 
    			 		   new Point(Double.valueOf(upperRightCoorLong), Double.valueOf(upperRightCoorLat)));
    	 criteria.and("location").within(box);
     }
    return new Query().addCriteria(criteria);
  }
}
