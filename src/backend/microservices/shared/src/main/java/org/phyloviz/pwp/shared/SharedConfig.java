package org.phyloviz.pwp.shared;

import org.phyloviz.pwp.shared.auth.FilterChainConfig;
import org.phyloviz.pwp.shared.auth.user.UserArgumentResolverConfig;
import org.phyloviz.pwp.shared.mongo.MongoConfig;
import org.phyloviz.pwp.shared.problem.ProblemJsonConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        UserArgumentResolverConfig.class,
        MongoConfig.class,
        ProblemJsonConfig.class,
        FilterChainConfig.class
})
public class SharedConfig {}
