package birt.eus.getyourroutebackend.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import birt.eus.getyourroutebackend.model.User;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

@Slf4j

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Itinerary not found")  // 404
public class ItineraryNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 14545345345L;
	
	public ItineraryNotFoundException(String idUser) {
		log.info("ItineraryNotFoundException Itinerary [{}] not found", idUser);
	}
	
	public ItineraryNotFoundException(LocalDateTime beginDate, LocalDateTime endDate) {
		log.info("ItineraryNotFoundException not found, for beginDate [{}] endDate [{}]", beginDate, endDate);
	}
	
	public ItineraryNotFoundException(User user) {
		log.info("ItineraryNotFoundException for User [{}] not found", user);
	}
	
	
	public ItineraryNotFoundException() {
		log.info("ItineraryNotFoundException Itinerarys not found");
	}
}
