package birt.eus.getyourroutebackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Geolocation parameter not found")  // 400
public class GeoLocationNotFoundParameterException extends RuntimeException {

	private static final long serialVersionUID = 145345345445L;
	
	public GeoLocationNotFoundParameterException(String parameter1, String parameter2) {
		log.info("GeoLocationNotFoundParameterException Geolocations parameters [{}] [{}] not found or empty", parameter1, parameter2);
	}

}
