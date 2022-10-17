package birt.eus.getyourroutebackend.model;

import com.mongodb.client.model.geojson.Point;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Getter
@Setter
@Document("geolocations")
public class GeoLocation {
    @Id
    private String id;
    private LocalDateTime timestamp;
    private String itineraryId;
    private String userId;
    private Point location;
	
    public GeoLocation(LocalDateTime timestamp, String itineraryId, String userId, Point location) {
		super();
		this.timestamp = timestamp;
		this.itineraryId = itineraryId;
		this.userId = userId;
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "GeoLocation [id=" + id + ", timestamp=" + timestamp + ", itineraryId=" + itineraryId + ", userId="
				+ userId + ", location=" + location + "]";
	}
}
