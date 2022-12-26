package birt.eus.getyourroutebackend.repository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import birt.eus.getyourroutebackend.model.GeoLocation;
import birt.eus.getyourroutebackend.model.Itinerary;
import birt.eus.getyourroutebackend.model.dto.ItineraryQueryParams;

@SpringBootTest
class ItineraryRepositoryTest {

  @Autowired
  private ItineraryRepository itineraryRepository;

 // @Autowired
 // private PointOfInterestRepository repository;
 
  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private GeoLocationRepository geoLocationRepository;

  @Test
  void testPagination() throws JsonProcessingException {
    ItineraryQueryParams itineraryQueryParams = new ItineraryQueryParams();
    itineraryQueryParams.setUserId("634dd15192a0cc18d740d7fb");
    Pageable pageable = PageRequest.of(1, 4);
    Page<Itinerary> page = itineraryRepository.findFiltered(itineraryQueryParams.getQuery(), pageable);
  }

  @Test
  void testRestaurantRepository() {
    GeoLocation geoLocation = new GeoLocation();
    geoLocation.setItineraryId("xx");
    geoLocation.setLocation(new GeoJsonPoint(12,12));
    geoLocationRepository.save(geoLocation);
  }
  @Test
  void testItem() throws JsonProcessingException {
    GeoLocation geoLocation = new GeoLocation();
    LocalDateTime now = LocalDateTime.now();
    geoLocation.setTimestamp(now.toInstant(ZoneOffset.UTC));
    String s = objectMapper.writeValueAsString(geoLocation);
    System.out.println(s);
  }

}
