package birt.eus.getyourroutebackend.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import birt.eus.getyourroutebackend.model.serializer.GeoJsonPointSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("geolocations")
public class GeoLocation {
    @Id
    private String id;
    private Instant timestamp;
    private String itineraryId;
    private String userId;
    @JsonSerialize(using = GeoJsonPointSerializer.class)
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2D, name="2dGeo_index_location")
    private GeoJsonPoint location;

    public GeoLocation(Instant timestamp, String itineraryId, String userId, GeoJsonPoint location) {
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
