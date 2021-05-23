package com.application.coffeemachine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class Time {

    final Long value;
    final TimeUnit unit;

    public Long getValueInMilliseconds() {
        // Send value in millis
        return value;
    }
}
