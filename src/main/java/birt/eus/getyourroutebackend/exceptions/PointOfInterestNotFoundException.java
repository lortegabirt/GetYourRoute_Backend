package birt.eus.getyourroutebackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Itinerary not found")  // 404
public class PointOfInterestNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 145445345L;
	
	public PointOfInterestNotFoundException(String id) {
		log.info("PointOfInterestNotFoundException PointOfInterest [{}] not found", id);
	}
		
	public PointOfInterestNotFoundException(String valor, String tipo) {
		log.info("PointOfInterestNotFoundException [{}] [{}] not found", valor, tipo);
	}

}