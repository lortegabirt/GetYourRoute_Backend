package birt.eus.getyourroutebackend.controller;

import java.util.List;

import birt.eus.getyourroutebackend.model.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import birt.eus.getyourroutebackend.exceptions.ItineraryNotFoundException;
import birt.eus.getyourroutebackend.exceptions.UserNotFoundException;
import birt.eus.getyourroutebackend.helper.GetYourRouteHelper;
import birt.eus.getyourroutebackend.model.Itinerary;
import birt.eus.getyourroutebackend.model.User;
import birt.eus.getyourroutebackend.model.dto.ItineraryQueryParams;
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
	 *  Lista todos los itinerarios, buscando por estos filtros
	 *
	 *  'userId', 'beginDate', 'endDate', 'name' expresion regular
	 *
	 * @return PageItineraryDTO
	 */
	@GetMapping({"/",""})
    public PageDto<Itinerary> index(
      @PageableDefault(size = Integer.MAX_VALUE, sort = {"beginDate"}, direction = Sort.Direction.DESC)
      Pageable pageable, ItineraryQueryParams itineraryQueryParams) {
      return new PageDto<>(itineraryRepository.findFiltered(itineraryQueryParams.getQuery(), pageable));
    }


	/**
	 * Pasandole un id obtiene el itinerario
	 *
	 * @param id String
	 * @return Itinerary
	 */
	@GetMapping("/id/{id}")
	public Itinerary showByID(@PathVariable("id") String id) {
		Itinerary itinerary = itineraryRepository.findById(id).orElse(null);
		if(itinerary == null) throw new ItineraryNotFoundException(id);
		return itinerary;
	}

	/**
	 * Obtener los itinerario de un usuario
	 *
	 * @param id String
	 * @return Itinerary
	 */
	@GetMapping("/userID/{userID}")
	public List<Itinerary> showByUserID(@PathVariable("userID") String userID) {
		List<Itinerary> listItinerarys = itineraryRepository.findByIdUser(userID);
		if(listItinerarys == null || listItinerarys.isEmpty()) throw new ItineraryNotFoundException("userID", userID);
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
}
