package com.application.coffeemachine.strategy;

import com.application.coffeemachine.model.Beverage;

import lombok.NonNull;

public interface IDisplayInsufficientIngredientsStrategy {

     void indicateInsufficientIngredients(@NonNull Beverage beverage);
}
