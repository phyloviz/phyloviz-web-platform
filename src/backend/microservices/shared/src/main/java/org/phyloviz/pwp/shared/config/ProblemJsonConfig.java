package org.phyloviz.pwp.shared.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.phyloviz.pwp.shared.http.pipeline.advice.ProblemJsonResponseBodyAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.zalando.problem.jackson.ProblemModule;

@Configuration
public class ProblemJsonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModule(new ProblemModule().withStackTraces(false))
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
