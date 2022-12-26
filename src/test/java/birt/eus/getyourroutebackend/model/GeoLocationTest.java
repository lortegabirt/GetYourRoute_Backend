package birt.eus.getyourroutebackend.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

class GeoLocationTest {

 @Test
  void testSerialization() throws JsonProcessingException {
    GeoLocation geoLocation =
      // new GeoLocation(LocalDateTime.now(), "id", "id", new Point(new Position(List.of(43D, -2D))));
    new GeoLocation(LocalDateTime.now().toInstant(ZoneOffset.UTC), "id", "id", new GeoJsonPoint(43D, -2D));
    ObjectMapper mapper = new ObjectMapper()
      .registerModule(new JavaTimeModule())
      .enable(SerializationFeature.INDENT_OUTPUT);
    System.out.println(mapper.writeValueAsString(geoLocation));
  }

  @Test
  void testItem() throws JsonProcessingException {
    GeoLocation geoLocation = new GeoLocation();
    LocalDateTime now = LocalDateTime.now();
    geoLocation.setTimestamp(now.toInstant(ZoneOffset.UTC));
    String s = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(geoLocation);
    System.out.println(s);
  }

}
