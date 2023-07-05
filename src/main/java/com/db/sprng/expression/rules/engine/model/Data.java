package com.db.sprng.expression.rules.engine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Data {
    String city;
    String evehicle;
    double amount;
    String gender;
    // @JsonIgnore
    double discountAmount;
    String discountRate;
    String state;
    String message;
}
