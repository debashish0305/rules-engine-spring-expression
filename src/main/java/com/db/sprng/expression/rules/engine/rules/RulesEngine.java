package com.db.sprng.expression.rules.engine.rules;

import com.db.sprng.expression.rules.engine.model.Data;
import com.db.sprng.expression.rules.engine.model.Rule;
import com.db.sprng.expression.rules.engine.model.Rules;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RulesEngine {
    private final RulesHelper rulesHelper;
  /*  private final ExpressionParser expressionParser;
    private final StandardEvaluationContext evaluationContext;
*/

    public Data evaluateRules(Data data) {
        Rules rules = rulesHelper.getAllRules();
        ExpressionParser expressionParser = new SpelExpressionParser();
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext(data);

        for (Rule rule : rules) {
            boolean result = Boolean.TRUE.equals(expressionParser.parseExpression(rule.getExpression()).getValue(evaluationContext, Boolean.class));
            if (result) {
                double discount = Double.parseDouble(rule.getDiscount());
                data.setDiscountRate(String.valueOf(discount * 100));
                double discountAmt = data.getAmount() * discount;
                data.setDiscountAmount(discountAmt);
                data.setAmount(data.getAmount() - discountAmt);
                String message = "Final price of the vehicle in " + data.getCity() + " is " + data.getAmount() + ", with discount rate of " + data.getDiscountRate() + " %";
                log.info("Spel Rules Engine evaluted..");
                log.info(message);
                data.setMessage(message);
                return data;
            }
        }
        Data d = new Data();
        d.setMessage("No discount");
        return d; // All rules evaluated failed
    }
}

