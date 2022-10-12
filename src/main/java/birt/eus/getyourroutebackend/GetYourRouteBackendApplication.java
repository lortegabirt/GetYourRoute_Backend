package birt.eus.getyourroutebackend;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import birt.eus.getyourroutebackend.model.Itinerary;
import birt.eus.getyourroutebackend.model.User;
import birt.eus.getyourroutebackend.repository.ItineraryRepository;
import birt.eus.getyourroutebackend.repository.UserRepository;

@SpringBootApplication
public class GetYourRouteBackendApplication implements CommandLineRunner {
	
  private static Logger log = LoggerFactory
		      .getLogger(GetYourRouteBackendApplication.class);	

  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private ItineraryRepository itineraryRepository;
  
  public static void main(String[] args) {
    SpringApplication.run(GetYourRouteBackendApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
	log.info ("#### START #### GetYourRouteBackendApplication CommandLineRunner");
	/* Descomentar para probar */
	/*
	User userFind = userRepository.findByEmail("lortega@birt.eus").orElse(null);

	log.info ("### Usuario encontrado name: [{}]", userFind.getName());
	itineraryRepository.save(createItinerary(userFind,"Itinerario1","descripcion1"));
	itineraryRepository.save(createItinerary(userFind,"Itinerario2","descripcion2"));
	itineraryRepository.save(createItinerary(userFind,"Itinerario3","descripcion3"));
	
	List<Itinerary> listaItinerarios = itineraryRepository.findByUser(userFind);
	log.info ("Itinerarios del usuario name: [{}]", userFind.getName());
	for (Itinerary itinerary : listaItinerarios) {
		 log.info ("Itinerario name: [{}]  user name: [{}]",itinerary.getName(),itinerary.getUser().getName());
	}
	*/
	log.info ("#### END #### GetYourRouteBackendApplication CommandLineRunner");
  }

  
  private Itinerary createItinerary(User user, String name, String description) {
	  Itinerary itinerary = new Itinerary(LocalDateTime.now(),LocalDateTime.now(),name,description,user);
	  return itinerary;
  }
}

