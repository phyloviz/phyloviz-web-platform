package org.phyloviz.pwp.compute.config;

import org.phyloviz.pwp.shared.SharedConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SharedConfig.class})
public class ComputeConfig {
}