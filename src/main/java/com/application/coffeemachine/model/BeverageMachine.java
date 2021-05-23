package com.application.coffeemachine.model;

import java.util.List;

import lombok.Getter;

@Getter

public class BeverageMachine {

	private final Inventory inventory;
	private final List<Outlet> outlets;
	private final List<Beverage> supportedBeverages;
	private final DisplayUnit displayUnit;

	private static BeverageMachine beverageMachine = null;

	private BeverageMachine(Inventory inventory, List<Outlet> outlets, List<Beverage> supportedBeverages,
			DisplayUnit displayUnit) {

		this.displayUnit = displayUnit;
		this.inventory = inventory;
		this.outlets = outlets;
		this.supportedBeverages = supportedBeverages;

	}

	public static BeverageMachine getBeverageMachineInstance(Inventory inventory, List<Outlet> outlets,
			List<Beverage> supportedBeverages, DisplayUnit displayUnit) {
		if (beverageMachine == null) {
			beverageMachine = new BeverageMachine(inventory, outlets, supportedBeverages, displayUnit);
		}
		return beverageMachine;

	}

}
