package org.phyloviz.pwp.shared_phylodb.config;

import org.phyloviz.pwp.shared.config.ResourceServerSharedConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ResourceServerSharedConfig.class})
@ComponentScan(basePackages = {"org.phyloviz.pwp.shared_phylodb", "pt.ist.meic.phylodb"})
public class ResourceServerSharedPhylodbConfig {

}