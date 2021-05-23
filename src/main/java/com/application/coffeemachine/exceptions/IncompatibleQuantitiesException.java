package com.application.coffeemachine.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IncompatibleQuantitiesException extends RuntimeException {
	
	String message;
	

}
