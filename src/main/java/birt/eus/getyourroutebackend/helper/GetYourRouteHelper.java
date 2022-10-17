package birt.eus.getyourroutebackend.helper;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import birt.eus.getyourroutebackend.repository.GeoLocationRepository;

/**
 * Clase de ayuda con metodos comunes usados en el proyecto.
 * 
 *
 */
public class GetYourRouteHelper {
	
	/**
	 * Borra los puntos de Localización de un itinerario
	 * 
	 * @param geoLocationRepository GeoLocationRepository
	 * @param idItinerary String
	 */
	public static void deleteGeoLocationsItinerary(GeoLocationRepository geoLocationRepository, String idItinerary) {
		List<String> listItinerarysId = geoLocationRepository.findByGeoLocationsIdItinerary(idItinerary);
		if (listItinerarysId != null && !listItinerarysId.isEmpty()) {
			geoLocationRepository.deleteAllById(listItinerarysId);
		}
	}
	
	/**
	 * Se pasa un id por ejemplo en este formato: { _id: ObjectId("634c302a02cf00533281b769") }
	 * y retorna solo el id 634c302a02cf00533281b769
	 * 
	 * @param id String
	 * @return String retorna solo el id
	 */
	public static String obtenerID(String id) {
		String idRet;
		String regExpr = "\".+\"";
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
