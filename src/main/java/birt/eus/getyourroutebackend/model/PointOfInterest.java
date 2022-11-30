package birt.eus.getyourroutebackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Document("points_of_interest")
public class PointOfInterest {
  @Id
  private String id;
  private FeatureType type;
  private String name;
  private GeoJsonPoint location;
  private Map<String, String> properties = new HashMap<>();
}
