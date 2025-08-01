package org.example.loggingFramework;

public class LogMessage {

    private String message;
    private LogLevel level;
    private long timestamp;
    private String threadName;

    public LogMessage(LogLevel level, String message) {
        this.message = message;
        this.level = level;
        this.timestamp = System.currentTimeMillis();
        this.threadName = Thread.currentThread().getName();
    }

    public String getMessage() {
        return message;
    }

    public LogLevel getLevel() {
        return level;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getThreadName() {
        return threadName;
    }
}
