package birt.eus.getyourroutebackend.helper;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	
	public String toStringID(String id) {
	    return String.format(
	            "TeamID[uniqueString=%s]",
	            id);
	}
}
