package com.pdy.fac.demorestback.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handler qui attrappe les erreurs propagées par les validateurs des WS afin de les convertir et de les retourner
 * 
 * @author PDY/Atos
 *
 */
@Order(1)
@ControllerAdvice
public class RestErrorsHandler {


	private static final Logger LOGGER = LoggerFactory.getLogger(RestErrorsHandler.class);


	@ExceptionHandler(ElementNotFound.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ResponseEntity<Void> handleNotFoundException(final ElementNotFound e) {
		LOGGER.error("L'entite n'a pas été trouvée", e);
		return ResponseEntity.notFound().build();
	}


}
