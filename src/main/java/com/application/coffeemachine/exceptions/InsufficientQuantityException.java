package com.application.coffeemachine.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InsufficientQuantityException extends RuntimeException {
	String message;
}
