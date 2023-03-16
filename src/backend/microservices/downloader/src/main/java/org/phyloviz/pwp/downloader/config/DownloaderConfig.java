package org.phyloviz.pwp.downloader.config;

import org.phyloviz.pwp.shared.auth.UserArgumentResolverConfig;
import org.phyloviz.pwp.shared.mongo.MongoConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({UserArgumentResolverConfig.class, MongoConfig.class})
public class DownloaderConfig {
}