package com.application.coffeemachine.strategy;

import com.application.coffeemachine.model.Beverage;
import com.application.coffeemachine.model.Outlet;
import lombok.NonNull;

import java.util.List;

public interface IOutletPickingStrategy {

    Outlet pickOutlet(@NonNull List<Outlet> outlets, @NonNull Beverage forBeverage);

	void releaseOutlet(Outlet outlet);
}
