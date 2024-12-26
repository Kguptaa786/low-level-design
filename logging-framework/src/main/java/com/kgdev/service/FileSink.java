package com.kgdev.service;

import java.io.FileWriter;
import java.io.IOException;

public class FileSink implements LogSink {
    private FileWriter fileWriter;
    public FileSink(String fileName) {
        try {
            fileWriter = new FileWriter(fileName, true);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void log(String message) {
        try {
            fileWriter.append(message).append("\n");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
