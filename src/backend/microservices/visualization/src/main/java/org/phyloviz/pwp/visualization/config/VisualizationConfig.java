package org.phyloviz.pwp.visualization.config;

import org.phyloviz.pwp.shared.config.ResourceServerSharedConfig;
import org.phyloviz.pwp.shared_phylodb.config.ResourceServerSharedPhylodbConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Configuration class for the Visualization Microservice.
 */
@Configuration
@Import({ResourceServerSharedConfig.class, ResourceServerSharedPhylodbConfig.class})
public class VisualizationConfig {
}