package birt.eus.getyourroutebackend;

import birt.eus.getyourroutebackend.model.Itinerary;
import birt.eus.getyourroutebackend.model.User;
import birt.eus.getyourroutebackend.repository.ItineraryRepository;
import birt.eus.getyourroutebackend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Disabled
@SpringBootTest
class ApplicationIntegrationTests {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ItineraryRepository itineraryRepository;

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
    }
  }

  private Itinerary createItinerary(User user, String name, String description) {
    Itinerary itinerary = new Itinerary(LocalDateTime.now(), LocalDateTime.now(), name, description, user);
    return itinerary;
  }

}
