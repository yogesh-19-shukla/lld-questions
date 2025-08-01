package org.example.loggingFramework;

public class ConsoleAppender implements Appender {
    @Override
    public void append(LogMessage message) {
        System.out.println("[" + message.getLevel() + "] [" + message.getThreadName() + "] "
                + new java.util.Date(message.getTimestamp()) + " - " + message.getMessage());
    }
}
