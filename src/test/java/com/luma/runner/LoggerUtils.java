package com.luma.runner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LoggerUtils {
    private static final Logger logger = LogManager.getLogger(BaseSelenium.class.getSimpleName());
    private static final String WARN = "⚠️";
    private static final String ERROR = "❌";
    private static final String FATAL = "❗";

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logWarning(String message) {
        logger.warn(WARN + message);
    }

    public static void logError(String message) {
        logger.error(ERROR + message);
    }

    public static void logFatal(String message) {
        logger.fatal(FATAL + message);
    }
}
