package birt.eus.getyourroutebackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Itinerary parameter not found")  // 400
public class ItineraryNotFoundParameterException extends RuntimeException {

	private static final long serialVersionUID = 14545345345445L;
	
	public ItineraryNotFoundParameterException(String parameter1, String parameter2) {
		log.info("ItineraryNotFoundException Itinerary parameters [{}] [{}] not found or empty", parameter1, parameter2);
	}

}
