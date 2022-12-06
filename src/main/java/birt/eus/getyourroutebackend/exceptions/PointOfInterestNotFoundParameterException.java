package birt.eus.getyourroutebackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="PointOfInterest parameters not found")  // 400
public class PointOfInterestNotFoundParameterException extends RuntimeException {

	private static final long serialVersionUID = 145453453545L;
	
	public PointOfInterestNotFoundParameterException(String parameters) {
		log.info("PointOfInterestNotFoundParameterException PointOfInterest parameters [{}] not found or empty", parameters);
	}

}
