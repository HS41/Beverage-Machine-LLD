package com.application.coffeemachine.model;

import com.application.coffeemachine.strategy.IDisplayInsufficientIngredientsStrategy;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class DisplayUnit {

    private final IDisplayInsufficientIngredientsStrategy iDisplayInsufficientIngredientsStrategy;
    public void display(@NonNull Beverage beverage) {
        iDisplayInsufficientIngredientsStrategy.indicateInsufficientIngredients(beverage);

    }
}
