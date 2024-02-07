package org.phyloviz.pwp.utils;

import org.slf4j.LoggerFactory;

/**
 * Logger utility class.
 */
public class SharedLogger {

    private static final String LOGGER_NAME = "pwp.logger";
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LOGGER_NAME);

    private SharedLogger() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Logs a message at the INFO level.
     *
     * @param message the message string to be logged
     */
    public static void info(String message) {
        LOGGER.info("[{}]: {}", System.currentTimeMillis(), message);
    }

    /**
     * Logs a message at the DEBUG level.
     *
     * @param message the message string to be logged
     */
    public static void debug(String message) {
        LOGGER.debug("[{}]: {}", System.currentTimeMillis(), message);
    }

    /**
     * Logs a message at the WARN level.
     *
     * @param message the message string to be logged
     */
    public static void warn(String message) {
        LOGGER.warn("[{}]: {}", System.currentTimeMillis(), message);
    }

    /**
     * Logs a message at the ERROR level.
     *
     * @param message the message string to be logged
     */
    public static void error(String message) {
        LOGGER.error("[{}]: {}", System.currentTimeMillis(), message);
    }

    /**
     * Logs a message at the TRACE level.
     *
     * @param message the message string to be logged
     */
    public static void trace(String message) {
        LOGGER.trace("[{}]: {}", System.currentTimeMillis(), message);
    }
}
