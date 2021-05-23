package com.application.coffeemachine.strategy;

import java.util.List;

import com.application.coffeemachine.model.Beverage;
import com.application.coffeemachine.model.Ingredient;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class DefaultDisplayInsufficientIngredientsStrategy implements IDisplayInsufficientIngredientsStrategy {@Override
	
	
	public void indicateInsufficientIngredients(@NonNull Beverage beverage) {
		
		List<Ingredient>ingredientList = beverage.getIngredients();
		
		for(Ingredient ingredient :ingredientList)
		{
			if(ingredient.getQuantity().getValue()<5)
			{
				System.out.println(ingredient.getIngredientDetails().getName()+" is running low. Please refil it ");
			}
			
		}
		
		
	}
}
