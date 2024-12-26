package com.kgdev.service;

import com.kgdev.enums.LogLevel;

import java.time.LocalDateTime;

public class Logger {
    private static Logger logger;
    private LogLevel logLevel;
    private LogSink logSink;
    private Logger(LogLevel logLevel, LogSink logSink){
        this.logLevel = logLevel;
        this.logSink = logSink;
    }
    public static Logger getInstance(LogLevel level, LogSink sink){
        if(logger == null){
            logger = new Logger(level, sink);
        }
        return logger;
    }
    public void info(String message) {
        log(LogLevel.INFO, message);
    }
    public void error(String message) {
        log(LogLevel.ERROR, message);
    }
    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }
    public void trace(String message) {
        log(LogLevel.TRACE, message);
    }
    public void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    public void setLogSink(LogSink logSink) {
        this.logSink = logSink;
    }
    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }
    private void log(LogLevel level, String message) {
        if (level.ordinal() >= logLevel.ordinal()) {
            String time = LocalDateTime.now().toString();
            String logMessage = time + "- [" + level + "] " + message;
            logSink.log(logMessage);
        }
    }

}
