package com.application.coffeemachine.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Beverage {

    private final String id;
    private final List<Ingredient> ingredients;
    private final Time time;
    private final String name;
}
