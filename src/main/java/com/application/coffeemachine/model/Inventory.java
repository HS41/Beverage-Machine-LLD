package com.application.coffeemachine.model;

import com.application.coffeemachine.exceptions.IngredientNotAvailableException;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    final Map<String, Ingredient> ingredients;

    public Inventory() {
        this.ingredients = new HashMap<>();
    }

    public void addIngredient(@NonNull final Ingredient ingredient) {
        final String ingredientId = ingredient.getIngredientDetails().getId();
        if (!ingredients.containsKey(ingredientId)) {
            ingredients.put(ingredientId, ingredient);
        } else  {
            ingredients.get(ingredientId).getQuantity().addQuantity(ingredient.getQuantity());
        }
    }

    public void consumeIngredient(@NonNull final Ingredient ingredient)  {
        final String ingredientId = ingredient.getIngredientDetails().getId();
        if (!ingredients.containsKey(ingredientId)) {
            throw new IngredientNotAvailableException(ingredient.getIngredientDetails().getName()+" Not Available.");
        } else  {
        	
        	
            ingredients.get(ingredientId).getQuantity().reduceQuantity(ingredient);
        }
    }
}
