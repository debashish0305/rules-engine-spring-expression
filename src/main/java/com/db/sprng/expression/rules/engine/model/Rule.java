package com.db.sprng.expression.rules.engine.model;

import lombok.Data;

@Data
public class Rule {
    String id;
    String name;
    String expression;
    String discount;
}
