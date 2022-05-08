package com.nps.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger= LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	
	@ExceptionHandler(value = {StockCountLimitExceedException.class})
	public ResponseEntity<Object> handleStockCountLimitExceedException(StockCountLimitExceedException ex) {
		logger.error("Exception: ", ex.getMessage());
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
