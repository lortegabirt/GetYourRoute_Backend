package birt.eus.getyourroutebackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import birt.eus.getyourroutebackend.exceptions.UserNotFoundException;
import birt.eus.getyourroutebackend.helper.GetYourRouteHelper;
import birt.eus.getyourroutebackend.model.Itinerary;
import birt.eus.getyourroutebackend.model.User;
import birt.eus.getyourroutebackend.repository.ItineraryRepository;
import birt.eus.getyourroutebackend.repository.UserRepository;

//@Slf4j

@RestController
@RequestMapping ("api/v0/users")
public class UserController  {
	  
	@Autowired
	ItineraryRepository itineraryRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private GetYourRouteHelper getYourRouteHelper;

	/**
	 *  Lista todos los usuarios
	 *  
	 * @return List<User>
	 */
	@GetMapping({"/",""})
	public List<User> index() {
		return userRepository.findAll();
	}

	/**
	 * Psandole un id obtiene el usuario
	 * 
	 * @param id String
	 * @return User
	 */
	@GetMapping("/id/{id}")
	public User showByID(@PathVariable("id") String id) {
		User user = userRepository.findById(id).orElse(null);
		if (user == null) throw new UserNotFoundException(id);
		return user;
	}
	
	/**
	 * Lista los usuarios por la expresión regular pasada en name
	 * 
	 * @param name String
	 * @return List<User> 
	 */
	@GetMapping("/name/{name}")
	public List<User> showByName(@PathVariable("name") String reg) {
		List<User> listUser = userRepository.findByName(reg);
		if (listUser == null || listUser.isEmpty()) throw new UserNotFoundException(reg);
		return listUser;
	}

	
	/**
	 * Actuliza un usuario, solo name, lastName y email
	 * 
	 * @param itinerary Itinerary
	 * @param id String
	 * @return Itinerary 
	 */
	@PutMapping("/{id}")
	@ResponseStatus (HttpStatus.CREATED)
	public User update(@RequestBody User user, @PathVariable("id") String id) {
		User tempUser = userRepository.findById(id).orElse(null);
		if (tempUser == null) throw new UserNotFoundException(id);
		tempUser.setName(user.getName());
		tempUser.setLastName(user.getLastName());
		tempUser.setEmail(user.getEmail());
		return userRepository.save(tempUser);
	}
	
	/**
	 * Borra un usuario
	 * 
	 * @param id String
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		User user = userRepository.findById(id).orElse(null);
		if (user == null) throw new UserNotFoundException(id);
		List<Itinerary> listItinerarys = itineraryRepository.findByUser(user);
		for (Itinerary itinerary : listItinerarys) {
			// Borra los puntos de localizacion del itinerario
			//String idOnly = GetYourRouteHelper.obtenerID(idItinerary);
			getYourRouteHelper.deleteGeoLocationsItinerary(itinerary.getId());
			// Borra el itinerario
			itineraryRepository.deleteById(itinerary.getId());
		}
		// Borra el usuario;
		//userRepository.deleteById(id);
	}
}
