package org.example.loggingFramework;

import java.util.List;

public class Logger {
    private final List<Appender> appenders;
    private final LogLevel level;

    public Logger(List<Appender> appenders, LogLevel level) {
        this.appenders = appenders;
        this.level = level;
    }

    private void log(LogLevel logLevel, String message) {
        if (logLevel.ordinal() >= level.ordinal()) {
            LogMessage logMessage = new LogMessage(logLevel, message);
            for (Appender appender : appenders) {
                appender.append(logMessage);
            }
        }
    }

    public void debug(String msg)  { log(LogLevel.DEBUG, msg); }
    public void info(String msg)   { log(LogLevel.INFO, msg); }
    public void warn(String msg)   { log(LogLevel.WARN, msg); }
    public void error(String msg)  { log(LogLevel.ERROR, msg); }
}