package birt.eus.getyourroutebackend.model;

import com.mongodb.client.model.geojson.Point;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("geolocations")
public class GeoLocation {
    @Id
    private String id;
    private LocalDateTime timestamp;
    private String itineraryId;
    private String userId;
    private Point location;
}
