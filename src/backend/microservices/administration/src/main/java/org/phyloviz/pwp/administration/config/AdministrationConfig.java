package org.phyloviz.pwp.administration.config;

import org.phyloviz.pwp.shared.config.ResourceServerSharedConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ResourceServerSharedConfig.class})
public class AdministrationConfig {}