package org.example.loggingFramework;

import java.util.Arrays;

public class LogManager {
    private static Logger logger;

    private LogManager() {}

    public static Logger getLogger() {
        if (logger == null) {
            synchronized (LogManager.class) {
                if (logger == null) {
                    logger = new Logger(Arrays.asList(new ConsoleAppender()), LogLevel.DEBUG);
                }
            }
        }
        return logger;
    }
}

