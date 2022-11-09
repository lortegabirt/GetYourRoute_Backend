package birt.eus.getyourroutebackend.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import birt.eus.getyourroutebackend.model.serializer.GeoJsonPointSerializer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("geolocations")
public class GeoLocation {
    @Id
    private String id;
    private LocalDateTime timestamp;
    private String itineraryId;
    private String userId;
    @JsonSerialize(using = GeoJsonPointSerializer.class)
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2D, name="2dGeo_index") 
    private GeoJsonPoint location;
    
    public GeoLocation(LocalDateTime timestamp, String itineraryId, String userId, GeoJsonPoint location) {
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
