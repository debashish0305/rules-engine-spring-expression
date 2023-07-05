package com.db.sprng.expression.rules.engine.rest;

import com.db.sprng.expression.rules.engine.model.Data;
import com.db.sprng.expression.rules.engine.model.Rules;
import com.db.sprng.expression.rules.engine.rules.RulesEngine;
import com.db.sprng.expression.rules.engine.rules.RulesHelper;
import com.db.sprng.expression.rules.engine.utils.EState;
import com.db.sprng.expression.rules.engine.utils.EVehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rules")
public class RuleController {


    private final RulesHelper rulesHelper;
    private final RulesEngine rulesEngine;

    @PostMapping //(value = "/endpoint", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> processRule(@RequestBody Data data) {
        Data d = rulesEngine.evaluateRules(data);
        return Mono.just(d.getMessage());
    }

    @GetMapping("/fetch")
    public Mono<Rules> getRule() {
        return Mono.just(rulesHelper.getAllRules());
    }
}
