package birt.eus.getyourroutebackend.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

@Slf4j

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="User not found")  // 404
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 14545345345345L;
	
	public UserNotFoundException(String idUser) {
		log.info("UserNotFoundException User [{}] noy found", idUser);
	}
}
