package com.application.coffeemachine.strategy;

import java.util.List;
import java.util.Map;

import com.application.coffeemachine.model.Beverage;
import com.application.coffeemachine.model.Outlet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class DefaultOutletPickingStrategy implements IOutletPickingStrategy {

	Map<String, Boolean> outletStatus;

	@Override
	public Outlet pickOutlet(@NonNull List<Outlet> outlets, @NonNull Beverage forBeverage) {

		for (Outlet outlet : outlets) {
			if (outletStatus.get(outlet.getId())) {
				outletStatus.put(outlet.getId(),false);
				return outlet;
			}
		}
		return null;
	}

	@Override
	public void releaseOutlet(Outlet outlet) {
		outletStatus.put(outlet.getId(),true);
		
	}

}
