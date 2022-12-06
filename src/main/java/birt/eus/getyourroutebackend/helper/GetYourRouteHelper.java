package birt.eus.getyourroutebackend.helper;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import birt.eus.getyourroutebackend.exceptions.PointOfInterestNotFoundParameterException;
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
	 * Rettorna en eun objeto String la fecha actual en formato "yyyy-MM-dd HH:mm:ss.SSS Z"
	 * 
	 * @return String fecha actual en "yyyy-MM-dd HH:mm:ss.SSS Z"
	 */
	public String getDateTimeNowFormat() {
		 DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS Z");
	     ZonedDateTime zonedDateTime = ZonedDateTime.now();
	     return fmt.format(zonedDateTime);
	}
	
	/**
	 * Valida los parametros de LocationNear
	 * Lanza la excepcion PointOfInterestNotFoundParameterException
	 * 
	 * @param paramsLocationNear Map<String, String>
	 */
	public boolean validateParamsLocationNear(Map<String, String> paramsLocationNear) {
		boolean ret = true;
		String parameters = new String();
		if (!paramsLocationNear.containsKey("lat")) {
			ret = false;
			parameters.concat("lat");
		} else if (!paramsLocationNear.containsKey("long")) {
			ret = false;
			parameters.concat(" long");
		} else if (!paramsLocationNear.containsKey("distance")) {
			ret = false;
			parameters.concat(" distance");
		}
		if (!ret) throw new PointOfInterestNotFoundParameterException(parameters);
		return ret;
	}
	

	/**
	 * Valida los parametros de LocationWithin
	 * Lanza la excepcion PointOfInterestNotFoundParameterException
	 * 
	 * @param paramsLocationWithin Map<String, String>
	 */
	public boolean validateParamsLocationWithin(Map<String, String> paramsLocationWithin) {
		boolean ret = true;
		String parameters = new String();
		if (!paramsLocationWithin.containsKey("bottomLeftCoorLat")) {
			ret = false;
			parameters.concat("bottomLeftCoorLat");
		} else if (!paramsLocationWithin.containsKey("bottomLeftCoorLong")) {
			ret = false;
			parameters.concat(" bottomLeftCoorLong");
		} else if (!paramsLocationWithin.containsKey("upperRightCoorLat")) {
			ret = false;
			parameters.concat(" upperRightCoorLat");
		} else if (!paramsLocationWithin.containsKey("upperRightCoorLong")) {
			ret = false;
			parameters.concat(" upperRightCoorLong");
		}
		if (!ret) throw new PointOfInterestNotFoundParameterException(parameters);
		return ret;
	}	
	
	public String toStringID(String id) {
	    return String.format(
	            "TeamID[uniqueString=%s]",
	            id);
	}
}
