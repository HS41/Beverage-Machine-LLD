package com.application.coffeemachine.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Ingredient {

    private final IngredientDetails ingredientDetails;
    private final Quantity quantity;
}
