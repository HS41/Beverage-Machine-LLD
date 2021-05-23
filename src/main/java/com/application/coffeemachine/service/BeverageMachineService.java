package com.application.coffeemachine.service;

import com.application.beveragemachine.BeverageCreator;
import com.application.coffeemachine.model.Beverage;
import com.application.coffeemachine.model.BeverageMachine;
import com.application.coffeemachine.model.Outlet;
import com.application.coffeemachine.strategy.IOutletPickingStrategy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class BeverageMachineService {

    private final BeverageMachine beverageMachine;
    private final IOutletPickingStrategy outletPickingStrategy;

    private void indicateInsufficientIngredientsIfNeeded(@NonNull final Beverage beverage) {
        
        beverageMachine.getDisplayUnit().display(beverage);
    }

    public void requestBeverage(@NonNull final Beverage beverage) {

        // Create queue in between.

        final BeverageCreator beverageCreator = new BeverageCreator(beverage, this);
        new Thread(beverageCreator).start();
        indicateInsufficientIngredientsIfNeeded(beverage);
    }

    public void dispatch(@NonNull final Beverage beverage) {
        final Outlet outlet = outletPickingStrategy.pickOutlet(beverageMachine.getOutlets(), beverage);
        
        if(outlet==null)
        {
        	System.out.println("All Outlets are in used . Please wait for sometime");
        	return ;
        }
        outlet.dispatch(beverage);
        outletPickingStrategy.releaseOutlet(outlet);
    }
}
