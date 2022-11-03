package birt.eus.getyourroutebackend.model;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class GeoLocationTest {

  @Test
  void testSerialization() throws JsonProcessingException {
    GeoLocation geoLocation =
      new GeoLocation(LocalDateTime.now(), "id", "id", new Point(new Position(List.of(43D, -2D))));
    ObjectMapper mapper = new ObjectMapper()
      .registerModule(new JavaTimeModule())
      .enable(SerializationFeature.INDENT_OUTPUT);
    System.out.println(mapper.writeValueAsString(geoLocation));
  }

}
