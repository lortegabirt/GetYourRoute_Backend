package birt.eus.getyourroutebackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import birt.eus.getyourroutebackend.exceptions.GeoLocationNotFoundException;
import birt.eus.getyourroutebackend.helper.GetYourRouteHelper;
import birt.eus.getyourroutebackend.model.GeoLocation;
import birt.eus.getyourroutebackend.model.dto.GeoLocationQueryParams;
import birt.eus.getyourroutebackend.model.dto.PageGeoLocationDTO;
import birt.eus.getyourroutebackend.repository.GeoLocationRepository;


@RestController
@RequestMapping ("api/v0/geolocations")
public class GeoLocationController  {
	
	@Autowired
	GeoLocationRepository geoLocationsRepository;
	
	@Autowired
	private GetYourRouteHelper getYourRouteHelper;

	/**
	 *  Lista todas las localizaciones, buscando por estos filtros
	 *  
	 *  beginDate endDate, itineraryId, userId
	 *  
	 * @return PageGeoLocationDTO
	 */
	@GetMapping({"/",""})
	public PageGeoLocationDTO index(@PageableDefault(size = Integer.MAX_VALUE) Pageable pageable, GeoLocationQueryParams geoLocationQueryParams) {
		Page<GeoLocation> pageGeoLocations = geoLocationsRepository.findFiltered(geoLocationQueryParams.getQuery(), pageable);	
		List<GeoLocation> listGeoLocations = pageGeoLocations.getContent();
		if (listGeoLocations == null || listGeoLocations.isEmpty()) throw new GeoLocationNotFoundException();
		PageGeoLocationDTO pageGeoLocationDTO = getYourRouteHelper.getPageGeoLocationDTO(pageGeoLocations, listGeoLocations);
		return pageGeoLocationDTO;
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
	 * Obtiene las localizaciones de un usuario
	 * 
	 * @param userID String
	 * @return List<GeoLocation>
	 */
	@GetMapping("/userID/{userID}")
	public List<GeoLocation> showByUserID(@PathVariable("userID") String userID) {
		List<GeoLocation> listGeoLocations = geoLocationsRepository.findByByGeoLocationsUserId(userID);
		if (listGeoLocations == null || listGeoLocations.isEmpty()) throw new GeoLocationNotFoundException("userID", userID);
		return listGeoLocations;
	}
	

	/**
	 * Obtiene las localizaciones de un itinerario
	 * 
	 * @param itineraryID String
	 * @return List<GeoLocation>
	 */
	@GetMapping("/itineraryID/{itineraryID}")
	public List<GeoLocation> showByItineraryID(@PathVariable("itineraryID") String itineraryID) {
		List<GeoLocation> listGeoLocations = geoLocationsRepository.findByByGeoLocationsItineraryId(itineraryID);
		if (listGeoLocations == null || listGeoLocations.isEmpty()) throw new GeoLocationNotFoundException("itineraryID", itineraryID);
		return listGeoLocations;
	}
	
	/**
	 * Crea una localizacion
	 * 
	 * @param itinerary Itinerary
	 * @return Itinerary
	 */
	@PostMapping({"/",""})
	@ResponseStatus (HttpStatus.CREATED)
	public GeoLocation create(@RequestBody GeoLocation geoLocation) {
		return geoLocationsRepository.save(geoLocation);
	}
	
	/**
	 * Actuliza una localizacion
	 * 
	 * @param itinerary Itinerary
	 * @param id String
	 * @return Itinerary 
	 */
	@PutMapping("/{id}")
	@ResponseStatus (HttpStatus.CREATED)
	public GeoLocation update(@RequestBody GeoLocation geoLocation, @PathVariable("id") String id) {
		GeoLocation tempGeoLocation = geoLocationsRepository.findById(id).orElse(null);
		if (tempGeoLocation == null) throw new GeoLocationNotFoundException(id);
		tempGeoLocation.setItineraryId(geoLocation.getItineraryId());
		tempGeoLocation.setUserId(geoLocation.getUserId());
		tempGeoLocation.setTimestamp(geoLocation.getTimestamp());
		tempGeoLocation.setLocation(geoLocation.getLocation());
		return geoLocationsRepository.save(tempGeoLocation);
	}
	
	/**
	 * Borra una localizacion
	 * 
	 * @param id String
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		if (!geoLocationsRepository.existsById(id)) throw new GeoLocationNotFoundException(id);
		geoLocationsRepository.deleteById(id);
	}

}
