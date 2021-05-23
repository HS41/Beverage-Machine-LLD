package com.application.coffeemachine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

//TODO: Can be converted into the interface.
@AllArgsConstructor
@Getter
public class Outlet {

	private final String id;

	public void dispatch(@NonNull final Beverage beverage) {
		System.out.println("Dispatching beverage: " + beverage.getName());
	}
}
