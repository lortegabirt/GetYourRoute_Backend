package birt.eus.getyourroutebackend;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import birt.eus.getyourroutebackend.model.GeoLocation;
import birt.eus.getyourroutebackend.model.Itinerary;
import birt.eus.getyourroutebackend.model.User;
import birt.eus.getyourroutebackend.repository.GeoLocationRepository;
import birt.eus.getyourroutebackend.repository.ItineraryRepository;
import birt.eus.getyourroutebackend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Disabled
@SpringBootTest
class ApplicationIntegrationTests {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ItineraryRepository itineraryRepository;

  @Autowired
  private GeoLocationRepository geoLocationRepository;

  
  double lat1 = 43.319639;
  double long1 = -3.028517;
  
  double lat2 = 43.259162;
  double long2 = -2.941313;
  
  double lat3 = 43.305650;
  double long3 = -2.904234;
  
  double lat4 = 43.356096;
  double long4 = -3.014955;
  
  double lat5 = 43.404805;
  double long5 = -2.946983;
  
  double lat6 = 43.352622;
  double long6 = -3.082716;

  double lat7 = 43.352622;
  double long7 = -3.082716;

  double[] latitudes = {lat1, lat2, lat3, lat4, lat5, lat6, lat7};
  double[] longitudes = {long1, long2, long3, long4, long5, long6, long7};
  
  @Test
  void contextLoads() {
  }

  @Test
  void testItineraryPersistence() {
    User userFind = userRepository.findByEmail("lortega@birt.eus").orElse(null);

    log.info("### Usuario encontrado name: [{}]", userFind.getName());
    itineraryRepository.save(createItinerary(userFind, "Itinerario1", "descripcion1"));
    itineraryRepository.save(createItinerary(userFind, "Itinerario2", "descripcion2"));
    itineraryRepository.save(createItinerary(userFind, "Itinerario3", "descripcion3"));

    List<Itinerary> listaItinerarios = itineraryRepository.findByUser(userFind);
    log.info("Itinerarios del usuario name: [{}]", userFind.getName());
    for (Itinerary itinerary : listaItinerarios) {
      log.info("Itinerario name: [{}]  user name: [{}]", itinerary.getName(), itinerary.getUser().getName());
      createGeoLocations(itinerary);
    }
  }

  private void createGeoLocations(Itinerary itinerary) {
	  Random randomLocations = new Random();
	  Random randomPoints = new Random();
	  int nLocations = randomLocations.nextInt(latitudes.length);
	  for (int i=0; i<=nLocations; i++) {
		  int nPoints = randomPoints.nextInt(latitudes.length);
		  double lat1 = latitudes[nPoints];
		  double long1 = longitudes[nPoints]; 
		  GeoLocation geoLocation = createGeoLocation(lat1, long1, itinerary.getId(), itinerary.getUser().getId());
		  geoLocationRepository.save(geoLocation);
	  }
  }
  
  private Itinerary createItinerary(User user, String name, String description) {
	Random randomHours = new Random();
	int hours = randomHours.nextInt(36);
	LocalDateTime begin =  LocalDateTime.now();
	LocalDateTime end =  LocalDateTime.now();
	end = end.plusHours(hours);
	log.info("### createItinerary beginDate: [{}] endDate: [{}]",begin, end);
	Itinerary itinerary = new Itinerary(begin.toInstant(ZoneOffset.UTC), end.toInstant(ZoneOffset.UTC), name, description, user);
    return itinerary;
  }

  private GeoLocation createGeoLocation(double latitude, double longitude, String itineraryId, String userId) {
	  GeoJsonPoint point1 = createPoint(latitude,longitude);
	  GeoLocation geoLocation = new GeoLocation(LocalDateTime.now().toInstant(ZoneOffset.UTC), itineraryId, userId, point1);
	  return geoLocation;
  }
  
  private GeoJsonPoint createPoint(double latitude, double longitude) {
	  GeoJsonPoint geoJsonPoint = new GeoJsonPoint(longitude, longitude);	  
	  log.info("### createPoint latitude: [{}] longitude: [{}]",latitude, longitude);
	 return geoJsonPoint;
  }
}
