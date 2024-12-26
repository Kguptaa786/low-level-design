package com.kgdev;

import com.kgdev.enums.LogLevel;
import com.kgdev.service.ConsoleSink;
import com.kgdev.service.LogSink;
import com.kgdev.service.Logger;

public class Main {
    public static void main(String[] args) {
        LogSink consoleSink = new ConsoleSink();
        Logger logger = Logger.getInstance(LogLevel.INFO, consoleSink);

        logger.info("info");
    }
}