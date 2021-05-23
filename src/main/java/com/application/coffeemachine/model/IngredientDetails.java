package com.application.coffeemachine.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IngredientDetails {

    private final String id;
    private final String name;
}
