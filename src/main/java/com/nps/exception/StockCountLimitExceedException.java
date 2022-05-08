package com.nps.exception;

public class StockCountLimitExceedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockCountLimitExceedException(String message) {
		super(message);
	}
	
}
