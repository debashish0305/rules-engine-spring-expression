package com.db.sprng.expression.rules.engine.rules;


import com.db.sprng.expression.rules.engine.model.Rule;
import com.db.sprng.expression.rules.engine.model.Rules;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * Read rules from file system/ DB/class path
 */
@Component
@Getter
public class RulesHelper {

    @Value("classpath:rules/rules.json")
    Resource resourceFile;

    public Rules getAllRules() {
        try {
            InputStream inputStream = getResourceFile().getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inputStream, Rules.class);
        } catch (IOException e) {
            throw new RuntimeException("IoException" + e.getMessage());
        }
    }


}
