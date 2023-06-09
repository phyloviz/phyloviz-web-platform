package org.phyloviz.pwp.file_transfer.config;

import org.phyloviz.pwp.shared.config.ResourceServerSharedConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Configuration class for the FileTransfer microservice.
 */
@Configuration
@Import({ResourceServerSharedConfig.class})
public class FileTransferConfig {
}