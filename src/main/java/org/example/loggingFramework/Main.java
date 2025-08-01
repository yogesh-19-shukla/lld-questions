package org.example.loggingFramework;

public class Main {

    public static void main(String[] args) {
        Logger logger = LogManager.getLogger();

        logger.info("System started.");
        logger.debug("Debugging info...");
        logger.warn("Low disk space!");
        logger.error("Unhandled exception!");
    }
}
