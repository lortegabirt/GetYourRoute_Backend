package birt.eus.getyourroutebackend.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

@Slf4j

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Itinerary not found")  // 404
public class ItineraryNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 14545345345L;
	
	public ItineraryNotFoundException(String idUser) {
		log.info("ItineraryNotFoundException Itinerary [{}] not found", idUser);
	}
}
