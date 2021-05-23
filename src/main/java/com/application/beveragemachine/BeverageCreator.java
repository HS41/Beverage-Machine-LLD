package com.application.beveragemachine;

import java.util.List;

import com.application.coffeemachine.exceptions.IngredientNotAvailableException;
import com.application.coffeemachine.exceptions.InsufficientQuantityException;
import com.application.coffeemachine.model.Beverage;
import com.application.coffeemachine.model.Ingredient;
import com.application.coffeemachine.model.Inventory;
import com.application.coffeemachine.service.BeverageMachineService;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class BeverageCreator implements Runnable {

	private final Beverage beverage;
	private final BeverageMachineService beverageMachineService;

	@SneakyThrows
	@Override
	public void run() {
		System.out.println("Starting to prepare " + beverage.getName());

		consume();
	}

	private synchronized void consume() {

		Boolean flag = true;
		Inventory inventory = beverageMachineService.getBeverageMachine().getInventory();
		List<Ingredient> ingredientlist = beverage.getIngredients();
		for (Ingredient ingredient : ingredientlist) {
			try {
				inventory.consumeIngredient(ingredient);
			} catch (IngredientNotAvailableException ex) {
				System.out.println(beverage.getName() + " Can't be prepared because "
						+ ingredient.getIngredientDetails().getName() + " Not Available");
				flag = false;
				break;

			} catch (InsufficientQuantityException ex) {
				System.out.println(beverage.getName() + " Can't be prepared because "
						+ ingredient.getIngredientDetails().getName() + " Not Sufficient");
				flag = false;
				break;

			}

		}
		if (flag) {
//			try {
//				System.out.println(beverage.getTime().getValueInMilliseconds());
//				//Thread.sleep(beverage.getTime().getValueInMilliseconds());
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			System.out.println(beverage.getName() + " is Prepared");
			 beverageMachineService.dispatch(beverage);
		}

	}
}
