package birt.eus.getyourroutebackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Geolocation not found")  // 404
public class GeoLocationNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1454534535645L;
	
	public GeoLocationNotFoundException() {
		log.info("GeoLocationNotFoundException Gelocation not found");
	}
	
	public GeoLocationNotFoundException(String id) {
		log.info("GeoLocationNotFoundException Gelocation id [{}] not found", id);
	}
}
