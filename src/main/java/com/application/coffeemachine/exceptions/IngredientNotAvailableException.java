package com.application.coffeemachine.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IngredientNotAvailableException extends RuntimeException {
	
	String message;
}
