package birt.eus.getyourroutebackend.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import birt.eus.getyourroutebackend.exceptions.GeoLocationNotFoundException;
import birt.eus.getyourroutebackend.exceptions.GeoLocationNotFoundParameterException;
import birt.eus.getyourroutebackend.exceptions.ItineraryNotFoundException;
import birt.eus.getyourroutebackend.exceptions.ItineraryNotFoundParameterException;
import birt.eus.getyourroutebackend.exceptions.UserNotFoundException;
import birt.eus.getyourroutebackend.helper.GetYourRouteHelper;
import birt.eus.getyourroutebackend.model.GeoLocation;
import birt.eus.getyourroutebackend.model.Itinerary;
import birt.eus.getyourroutebackend.model.User;
import birt.eus.getyourroutebackend.repository.GeoLocationRepository;
import birt.eus.getyourroutebackend.repository.ItineraryRepository;
import birt.eus.getyourroutebackend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j

@RestController
@RequestMapping ("api/v0/geolocations")
public class GeoLocationController  {
	  
	@Autowired
	ItineraryRepository itineraryRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	GeoLocationRepository geoLocationsRepository;
	
	@Autowired
	private GetYourRouteHelper getYourRouteHelper;

	/**
	 *  Lista todas las localizaciones
	 *  
	 * @return Map<String, Object> 
	 */
	@GetMapping({"/",""})
	public Map<String, Object> index(@RequestParam Map<String, String> filters) {
		List<GeoLocation> listGeoLocations;
		Map<String, Object> response = new HashMap<>();
		Page<GeoLocation> pageLocations = null;
		Pageable paging = getYourRouteHelper.getRequestParamPageSize(filters);
		if (paging!=null) {
			pageLocations = geoLocationsRepository.findAll(paging);
			listGeoLocations = pageLocations.getContent();
			if (listGeoLocations == null || listGeoLocations.isEmpty()) throw new GeoLocationNotFoundException();
			response.put("geolocations", listGeoLocations);
		    response.put("currentPage", pageLocations.getNumber());
		    response.put("totalItems", pageLocations.getTotalElements());
		    response.put("totalPages", pageLocations.getTotalPages());
		} else {
			throw new GeoLocationNotFoundParameterException("page","size");
		}		
		return response;
	}

	/**
	 * Psandole un id obtiene la localizacion
	 * @param id String
	 * @return GeoLocation
	 */
	@GetMapping("/id/{id}")
	public GeoLocation showByID(@PathVariable("id") String id) {
		GeoLocation geoLocation = geoLocationsRepository.findById(id).orElse(null);
		if(geoLocation == null) throw new GeoLocationNotFoundException(id);;
		return geoLocation;
	}
	
	/**
	 * Lista los itinerarios buscando por fecha de inicio y fin 
	 * 
	 * Las fechas tienen que tener el formato 
	 * 	Ejemplos
	 * 		2022-11-03T04:52:22.999
	 * 		2022-11-03T13:52:23
	 * 		2023-12-25T03:00:00.000
	 *
	 * @param beginDate String
	 * @param endDate String
	 * @return List<Itinerary>
	 */
	
	@GetMapping("/date")
	public List<Itinerary> showByDate(@RequestParam Map<String, String> filters) {
		String beginDate = filters.get("beginDate");
		String endDate = filters.get("endDate");
		if (beginDate==null || "".equals(beginDate) || endDate==null || "".equals(endDate)) { throw new ItineraryNotFoundParameterException("begiDate", "endDate"); }
		LocalDateTime beginDateLocal = LocalDateTime.parse(beginDate); 
		LocalDateTime endDateLocal = LocalDateTime.parse(endDate);
		List<Itinerary> listItinerarys = itineraryRepository.findByBeginDateEndDate(beginDateLocal, endDateLocal);
		if (listItinerarys == null || listItinerarys.isEmpty()) throw new ItineraryNotFoundException(beginDateLocal, endDateLocal);
		return listItinerarys;
	}

	
	/**
	 * Lista los itinerarios por nombre
	 * 
	 * @param name String
	 * @return List<Itinerary> 
	 */
	@GetMapping("/name/{name}")
	public List<Itinerary> showByName(@PathVariable("name") String name) {
		List<Itinerary> listItinerarys = itineraryRepository.findByName(name);
		if (listItinerarys == null || listItinerarys.isEmpty()) throw new ItineraryNotFoundException(name);
		return listItinerarys;
	}

	/**
	 * Lista los itinerarios por la expresión regular pasada en name
	 * 
	 * @param name String
	 * @return List<Itinerary> 
	 */
	@GetMapping("/nameExpr/{name}")
	public List<Itinerary> showByNameExpr(@PathVariable("name") String reg) {
		List<Itinerary> listItinerarys = itineraryRepository.findByNameExpr(reg);
		if (listItinerarys == null || listItinerarys.isEmpty()) throw new ItineraryNotFoundException(reg);
		return listItinerarys;
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
		if (userFind == null) throw new UserNotFoundException(userID);
		List<Itinerary> listItinerarys = itineraryRepository.findByUser(userFind);
		if (listItinerarys == null || listItinerarys.isEmpty()) throw new ItineraryNotFoundException(userFind);
		return listItinerarys;
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
		if (tempItinerary == null) throw new ItineraryNotFoundException(id);
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
