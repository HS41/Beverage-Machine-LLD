package com.application.beveragemachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;

import com.application.coffeemachine.model.Beverage;
import com.application.coffeemachine.model.BeverageMachine;
import com.application.coffeemachine.model.DisplayUnit;
import com.application.coffeemachine.model.Ingredient;
import com.application.coffeemachine.model.IngredientDetails;
import com.application.coffeemachine.model.Inventory;
import com.application.coffeemachine.model.Measurement;
import com.application.coffeemachine.model.Outlet;
import com.application.coffeemachine.model.Quantity;
import com.application.coffeemachine.model.Time;
import com.application.coffeemachine.model.TimeUnit;
import com.application.coffeemachine.service.BeverageMachineService;
import com.application.coffeemachine.strategy.DefaultDisplayInsufficientIngredientsStrategy;
import com.application.coffeemachine.strategy.DefaultOutletPickingStrategy;
import com.application.coffeemachine.strategy.IDisplayInsufficientIngredientsStrategy;
import com.application.coffeemachine.strategy.IOutletPickingStrategy;

public class BeverageMachineTest {

	private BeverageMachine beverageMachine;
	private BeverageMachineService beverageMachineService;

	@Before
	public void init() {
		Inventory inventory = initializeInventory();
		List<Outlet> outlets = initializeOutlets();
		List<Beverage> supportedBeverages = initializeSupportedBeverages();
		DisplayUnit displayUnit = initializeDisplayUnit();
		beverageMachine = BeverageMachine.getBeverageMachineInstance(inventory, outlets, supportedBeverages,
				displayUnit);

		Map<String, Boolean> outletStatus = new HashMap<String, Boolean>();
		for (Outlet outlet : beverageMachine.getOutlets())
			outletStatus.put(outlet.getId(), true);
		IOutletPickingStrategy outletPickingStrategy = new DefaultOutletPickingStrategy(outletStatus);
		beverageMachineService = new BeverageMachineService(beverageMachine, outletPickingStrategy);
	}

	private DisplayUnit initializeDisplayUnit() {
		IDisplayInsufficientIngredientsStrategy strategy = new DefaultDisplayInsufficientIngredientsStrategy();
		return new DisplayUnit(strategy);

	}

	private List<Beverage> initializeSupportedBeverages() {

		List<Beverage> listBeverages = new ArrayList<Beverage>();

		Beverage HotCoffee = Beverage.builder().name("Hot_Coffee").id("1")
				.time(Time.builder().unit(TimeUnit.MILISECOND).value(10L).build())
				.ingredients(getHotCoffeeIngredients()).build();

		Beverage HotTea = Beverage.builder().name("Hot_Tea").id("2")
				.time(Time.builder().unit(TimeUnit.MILISECOND).value(10L).build()).ingredients(getHotTeaIngredients())
				.build();

		Beverage BlackTea = Beverage.builder().name("Black_Tea").id("3")
				.time(Time.builder().unit(TimeUnit.MILISECOND).value(10L).build()).ingredients(getBlackTeaIngredients())
				.build();

		Beverage Greentea = Beverage.builder().name("Green_Tea").id("4")
				.time(Time.builder().unit(TimeUnit.MILISECOND).value(10L).build()).ingredients(getGreenTeaIngredients())
				.build();

		listBeverages.add(HotCoffee);
		listBeverages.add(HotTea);
		listBeverages.add(BlackTea);
		listBeverages.add(Greentea);
		return listBeverages;

	}

	private List<Ingredient> getHotTeaIngredients() {

		List<Ingredient> list = new ArrayList<Ingredient>();
		list.add(Ingredient.builder().ingredientDetails(IngredientDetails.builder().id("1").name("Hot Water").build())
				.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(200).build()).build());

		list.add(
				Ingredient.builder().ingredientDetails(IngredientDetails.builder().id("2").name("Ginger Syrup").build())
						.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(10).build()).build());

		list.add(Ingredient.builder().ingredientDetails(IngredientDetails.builder().id("3").name("Hot Milk").build())
				.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(100).build()).build());

		list.add(Ingredient.builder().ingredientDetails(IngredientDetails.builder().id("4").name("Sugar Syrup").build())
				.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(10).build()).build());

		list.add(Ingredient.builder()
				.ingredientDetails(IngredientDetails.builder().id("5").name("Tea leaves Syrup").build())
				.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(30).build()).build());
		return list;
	}

	private List<Ingredient> getGreenTeaIngredients() {

		List<Ingredient> list = new ArrayList<Ingredient>();
		list.add(Ingredient.builder().ingredientDetails(IngredientDetails.builder().id("1").name("Hot Water").build())
				.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(100).build()).build());

		list.add(
				Ingredient.builder().ingredientDetails(IngredientDetails.builder().id("2").name("Ginger Syrup").build())
						.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(30).build()).build());

		list.add(Ingredient.builder().ingredientDetails(IngredientDetails.builder().id("4").name("Sugar Syrup").build())
				.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(50).build()).build());

		list.add(Ingredient.builder()
				.ingredientDetails(IngredientDetails.builder().id("6").name("Green Mixture").build())
				.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(30).build()).build());
		return list;
	}

	private List<Ingredient> getBlackTeaIngredients() {

		List<Ingredient> list = new ArrayList<Ingredient>();
		list.add(Ingredient.builder().ingredientDetails(IngredientDetails.builder().id("1").name("Hot Water").build())
				.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(300).build()).build());

		list.add(
				Ingredient.builder().ingredientDetails(IngredientDetails.builder().id("2").name("Ginger Syrup").build())
						.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(30).build()).build());

		list.add(Ingredient.builder().ingredientDetails(IngredientDetails.builder().id("4").name("Sugar Syrup").build())
				.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(50).build()).build());

		list.add(Ingredient.builder()
				.ingredientDetails(IngredientDetails.builder().id("5").name("Tea leaves Syrup").build())
				.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(30).build()).build());
		return list;
	}

	private List<Ingredient> getHotCoffeeIngredients() {

		List<Ingredient> list = new ArrayList<Ingredient>();
		list.add(Ingredient.builder().ingredientDetails(IngredientDetails.builder().id("1").name("Hot Water").build())
				.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(100).build()).build());

		list.add(
				Ingredient.builder().ingredientDetails(IngredientDetails.builder().id("2").name("Ginger Syrup").build())
						.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(30).build()).build());

		list.add(Ingredient.builder().ingredientDetails(IngredientDetails.builder().id("3").name("Hot Milk").build())
				.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(400).build()).build());

		list.add(Ingredient.builder().ingredientDetails(IngredientDetails.builder().id("4").name("Sugar Syrup").build())
				.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(50).build()).build());

		list.add(Ingredient.builder()
				.ingredientDetails(IngredientDetails.builder().id("5").name("Tea leaves Syrup").build())
				.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(30).build()).build());
		return list;
	}

	private List<Outlet> initializeOutlets() {

		int noOfOutlets = 3;
		List<Outlet> outlets = new ArrayList<Outlet>(noOfOutlets);

		outlets.add(new Outlet("1"));
		outlets.add(new Outlet("2"));
		outlets.add(new Outlet("3"));

		return outlets;

	}

	private Inventory initializeInventory() {

		Inventory inventory = new Inventory();
		Ingredient HotWater = Ingredient.builder()
				.ingredientDetails(IngredientDetails.builder().id("1").name("Hot Water").build())
				.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(500).build()).build();

		Ingredient HotMilk = Ingredient.builder()
				.ingredientDetails(IngredientDetails.builder().id("3").name("Hot Milk").build())
				.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(500).build()).build();

		Ingredient GingerSyrp = Ingredient.builder()
				.ingredientDetails(IngredientDetails.builder().id("2").name("Ginger Syrup").build())
				.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(100).build()).build();

		Ingredient SugarSyrup = Ingredient.builder()
				.ingredientDetails(IngredientDetails.builder().id("4").name("Sugar Syrup").build())
				.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(100).build()).build();

		Ingredient TeaLeavesSyrup = Ingredient.builder()
				.ingredientDetails(IngredientDetails.builder().id("5").name("Tea Leaves Syrup").build())
				.quantity(Quantity.builder().measurement(Measurement.MILILITRE).value(100).build()).build();
		inventory.addIngredient(HotWater);
		inventory.addIngredient(HotMilk);
		inventory.addIngredient(GingerSyrp);
		inventory.addIngredient(SugarSyrup);
		inventory.addIngredient(TeaLeavesSyrup);
		return inventory;

	}

	@Test
	public void singleOrderAtATime() {

		beverageMachineService.requestBeverage(beverageMachine.getSupportedBeverages().get(0));
		beverageMachineService.requestBeverage(beverageMachine.getSupportedBeverages().get(1));
		beverageMachineService.requestBeverage(beverageMachine.getSupportedBeverages().get(2));

	}

	@Test
	public void parallelOrders() {

		ExecutorService executorService = Executors.newFixedThreadPool(beverageMachine.getOutlets().size());
		Future order1 = executorService.submit(() -> {
			beverageMachineService.requestBeverage(beverageMachine.getSupportedBeverages().get(0));

		});
		Future order2 = executorService.submit(() -> {
			beverageMachineService.requestBeverage(beverageMachine.getSupportedBeverages().get(1));

		});

	}

}
