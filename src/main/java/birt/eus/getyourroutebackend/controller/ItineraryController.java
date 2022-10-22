package birt.eus.getyourroutebackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import birt.eus.getyourroutebackend.exceptions.ItineraryNotFoundException;
import birt.eus.getyourroutebackend.exceptions.UserNotFoundException;
import birt.eus.getyourroutebackend.helper.GetYourRouteHelper;
import birt.eus.getyourroutebackend.model.Itinerary;
import birt.eus.getyourroutebackend.model.User;
import birt.eus.getyourroutebackend.repository.ItineraryRepository;
import birt.eus.getyourroutebackend.repository.UserRepository;


@RestController
@RequestMapping ("api/v0/itinerarys")
public class ItineraryController  {
	  
	@Autowired
	ItineraryRepository itineraryRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private GetYourRouteHelper getYourRouteHelper;

	/**
	 *  Lista todos los itinerarios
	 *  
	 * @return List<Itinerary>
	 */
	@GetMapping({"/",""})
	public List<Itinerary> index() {
		return itineraryRepository.findAll();
	}

	/**
	 * Psandole un id obtiene el itinerario
	 * @param id String
	 * @return Itinerary
	 */
	@GetMapping("/id/{id}")
	public Itinerary showByID(@PathVariable("id") String id) {
		return itineraryRepository.findById(id).orElse(null);
	}
	
	/**
	 * Lista los itinerarios por nombre
	 * 
	 * @param name String
	 * @return List<Itinerary> 
	 */
	@GetMapping("/name/{name}")
	public List<Itinerary> showByName(@PathVariable("name") String name) {
		return itineraryRepository.findByName(name);
	}

	/**
	 * Obtiene los itinerarios de un usuario
	 * 
	 * @param userID String
	 * @return List<Itinerary>
	 */
	@GetMapping("/userID/{userID}")
	public List<Itinerary> showByUserID(@PathVariable("userID") String userID) {
		User userFind = userRepository.findById(userID).orElse(null);
		return itineraryRepository.findByUser(userFind);
	}
	
	/**
	 * Crea un itinerario
	 * 
	 * @param itinerary Itinerary
	 * @return Itinerary
	 */
	@PostMapping({"/",""})
	@ResponseStatus (HttpStatus.CREATED)
	public Itinerary create(@RequestBody Itinerary itinerary) {
		return itineraryRepository.save(itinerary);
	}
	/**
	 * Actuliza un itinerario
	 * 
	 * @param itinerary Itinerary
	 * @param id String
	 * @return Itinerary 
	 */
	@PutMapping("/{id}")
	@ResponseStatus (HttpStatus.CREATED)
	public Itinerary update(@RequestBody Itinerary itinerary, @PathVariable("id") String id) {
		Itinerary tempItinerary = itineraryRepository.findById(id).orElse(null);
		
		tempItinerary.setName(itinerary.getName());
		tempItinerary.setDescription(itinerary.getDescription());
		tempItinerary.setBeginDate(tempItinerary.getBeginDate());
		tempItinerary.setEndDate(tempItinerary.getEndDate());
		tempItinerary.setUser(tempItinerary.getUser());
		
		return itineraryRepository.save(tempItinerary);
	}
	
	/**
	 * Borra un itinerario
	 * 
	 * @param id String
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		if (!itineraryRepository.existsById(id)) throw new ItineraryNotFoundException(id);
		getYourRouteHelper.deleteGeoLocationsItinerary(id);
		itineraryRepository.deleteById(id);
	}

	/**
	 * Borra los itinerarios de un usuario
	 * 
	 * @param id String
	 */
	@DeleteMapping("/delelteitineraryuser/{userid}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void deleteItinerarysUser(@PathVariable("userid") String userID) {
		User userFind = userRepository.findById(userID).orElse(null);
		if (userFind==null) throw new UserNotFoundException(userID);
		List<Itinerary> listaItinerarios = itineraryRepository.findByUser(userFind);
		for (Itinerary itinerary : listaItinerarios) {
			itineraryRepository.deleteById(itinerary.getId());
		}
	}
	
	/*
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad Request")
	@ExceptionHandler(IllegalArgumentException.class)
	public String handleException (IllegalArgumentException ex) {
		return ex.getMessage();
	}
	*/
}
