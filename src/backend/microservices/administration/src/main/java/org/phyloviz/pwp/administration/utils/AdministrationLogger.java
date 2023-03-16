package org.phyloviz.pwp.administration.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logger utility class.
 */
public class AdministrationLogger {

    private final static String loggerName = "administration.logger";
    private final static Logger logger = LoggerFactory.getLogger(loggerName);

    /**
     * Logs a message at the INFO level.
     *
     * @param message the message string to be logged
     */
    public static void info(String message) {
        logger.info("[" + System.currentTimeMillis() + "]: " + message);
    }

    /**
     * Logs a message at the DEBUG level.
     *
     * @param message the message string to be logged
     */
    public static void debug(String message) {
        logger.debug("[" + System.currentTimeMillis() + "]: " + message);
    }

    /**
     * Logs a message at the WARN level.
     *
     * @param message the message string to be logged
     */
    public static void warn(String message) {
        logger.warn("[" + System.currentTimeMillis() + "]: " + message);
    }

    /**
     * Logs a message at the ERROR level.
     *
     * @param message the message string to be logged
     */
    public static void error(String message) {
        logger.error("[" + System.currentTimeMillis() + "]: " + message);
    }

    /**
     * Logs a message at the TRACE level.
     *
     * @param message the message string to be logged
     */
    public static void trace(String message) {
        logger.trace("[" + System.currentTimeMillis() + "]: " + message);
    }
}
