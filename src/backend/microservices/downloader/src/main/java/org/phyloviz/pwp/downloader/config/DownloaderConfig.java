package org.phyloviz.pwp.downloader.config;

import org.phyloviz.pwp.shared.config.SharedConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SharedConfig.class})
public class DownloaderConfig {
}