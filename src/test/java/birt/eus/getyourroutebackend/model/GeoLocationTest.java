package birt.eus.getyourroutebackend.model;

import java.time.LocalDateTime;

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
    new GeoLocation(LocalDateTime.now(), "id", "id", new GeoJsonPoint(43D, -2D));
    ObjectMapper mapper = new ObjectMapper()
      .registerModule(new JavaTimeModule())
      .enable(SerializationFeature.INDENT_OUTPUT);
    System.out.println(mapper.writeValueAsString(geoLocation));
  }

}
