package com.application.coffeemachine.model;

import com.application.coffeemachine.exceptions.IncompatibleQuantitiesException;
import com.application.coffeemachine.exceptions.InsufficientQuantityException;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class Quantity {

	private Integer value;
	private final Measurement measurement;

	public void addQuantity(@NonNull final Quantity newQuantity) {
		if (!this.getMeasurement().equals(newQuantity.getMeasurement())) {
			throw new IncompatibleQuantitiesException("");
		}

		this.value += newQuantity.getValue();
	}

	public void reduceQuantity(@NonNull final Ingredient ingredient) {

		Quantity otherQuantity = ingredient.getQuantity();
		if (!this.getMeasurement().equals(otherQuantity.getMeasurement())) {
			throw new IncompatibleQuantitiesException(
					otherQuantity.getMeasurement() + " Not Compatible with " + this.getMeasurement());
		}

		if (this.value < otherQuantity.getValue()) {
			throw new InsufficientQuantityException(ingredient.getIngredientDetails().getName()+" is Insufficient");
		}
		this.value -= otherQuantity.getValue();
	}
}
