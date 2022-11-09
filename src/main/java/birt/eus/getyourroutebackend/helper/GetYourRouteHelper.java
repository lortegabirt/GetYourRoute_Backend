package birt.eus.getyourroutebackend.helper;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import birt.eus.getyourroutebackend.model.GeoLocation;
import birt.eus.getyourroutebackend.model.Itinerary;
import birt.eus.getyourroutebackend.model.User;
import birt.eus.getyourroutebackend.model.dto.PageGeoLocationDTO;
import birt.eus.getyourroutebackend.model.dto.PageItineraryDTO;
import birt.eus.getyourroutebackend.model.dto.PageUserDTO;
import birt.eus.getyourroutebackend.repository.GeoLocationRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j


/**
 * Clase de ayuda con metodos comunes usados en el proyecto.
 * 
 *
 */
@Component
public class GetYourRouteHelper {
	
	@Autowired
	private GeoLocationRepository geoLocationRepository;

	/**
	 * Borra los puntos de Localización de un itinerario
	 * 
	 * @param geoLocationRepository GeoLocationRepository
	 * @param idItinerary String
	 */
	public void deleteGeoLocationsItinerary(String idItinerary) {
		List<String> listItinerarysId = geoLocationRepository.findByGeoLocationsIdItinerary(idItinerary);
		log.info("GetYourRouteHelper.deleteGeoLocationsItinerary listItinerarysId [{}]", listItinerarysId);
		for (String iDItinerary : listItinerarysId) {
			iDItinerary = obtenerID(iDItinerary);
			geoLocationRepository.deleteById(iDItinerary);
		}
	}
	
	/**
	 * Se pasa un id por ejemplo en este formato: {"_id": "635431eea4e5a8148801c865"}
	 * y retorna solo el id 635431eea4e5a8148801c865
	 * 
	 * @param id String
	 * @return String retorna solo el id
	 */
	public static String obtenerID(String id) {
		String idRet;
		String regExpr = "\\\"[\\w\\d]{24}\\\"";
		Pattern pattern = Pattern.compile(regExpr);
		Matcher matcher = pattern.matcher(id);
		if (matcher.find()) {
			idRet=matcher.group();
			// Eliminar las comillas de comienzo y fin
			idRet=idRet.substring(1, idRet.length()-1);
		} else {
			idRet=id;
		}
		return idRet;
	}
	
	/**
	 * Si se ha puesto los parametros  en la request 'page' y 'size' retorna el objeto Pageable
	 * en caso contrario null
	 * 
	 * @param filters
	 * @return Pageable or null
	 */
	public Pageable getRequestParamPageSize(Map<String, String> filters) {
		
		String page = filters.get("page");
		String size = filters.get("pageSize");
		
		if (page==null || size==null || "".equals(page) || "".equals(size)) {
			return PageRequest.of(0, Integer.MAX_VALUE);
		} else {
			return PageRequest.of((Integer.parseInt(page)-1), Integer.parseInt(size));
		}
	}

	/**
	 * Retorna el objeto PageItineraryDTO rellenado con los datos que se le pasan en pageItinerarys y listItinerarys
	 * 
	 * @param pageItinerarys Page<Itinerary>
	 * @param listItinerarys List<Itinerary>
	 * @return PageItineraryDTO
	 */
	public PageItineraryDTO getPageItineraryDTO(Page<Itinerary> pageItinerarys, List<Itinerary> listItinerarys) {
		PageItineraryDTO pageItineraryDTO = new PageItineraryDTO((pageItinerarys.getNumber()+1), 
			    pageItinerarys.getNumberOfElements(),
			    pageItinerarys.getTotalElements(),
			    pageItinerarys.getTotalPages(),
			    listItinerarys);
		return pageItineraryDTO;
	}

	/**
	 * Retorna el objeto UserItineraryDTO rellenado con los datos que se le pasan en pageItinerarys y listItinerarys
	 * 
	 * @param pageItinerarys Page<Itinerary>
	 * @param listItinerarys List<Itinerary>
	 * @return UserItineraryDTO
	 */
	public PageUserDTO getPageUserDTO(Page<User> pageUsers, List<User> listUsers) {
		PageUserDTO pageUserDTO = new PageUserDTO((pageUsers.getNumber()+1), 
				pageUsers.getNumberOfElements(),
				pageUsers.getTotalElements(),
				pageUsers.getTotalPages(),
				listUsers);
		return pageUserDTO;
	}

	/**
	 * Retorna el objeto PageGeoLocationDTO rellenado con los datos que se le pasan en pageGeoLocations y listGeoLocations
	 * 
	 * @param pageGeoLocations Page<Itinerary>
	 * @param listGeoLocations List<Itinerary>
	 * @return PageGeoLocationDTO
	 */
	public PageGeoLocationDTO getPageGeoLocationDTO(Page<GeoLocation> pageGeoLocations, List<GeoLocation> listGeoLocations) {
		PageGeoLocationDTO pageGeoLocationDTO = new PageGeoLocationDTO((pageGeoLocations.getNumber()+1), 
				pageGeoLocations.getNumberOfElements(),
				pageGeoLocations.getTotalElements(),
				pageGeoLocations.getTotalPages(),
				listGeoLocations);
		return pageGeoLocationDTO;
	}

	public String toStringID(String id) {
	    return String.format(
	            "TeamID[uniqueString=%s]",
	            id);
	}
}
