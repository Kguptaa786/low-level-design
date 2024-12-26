package com.kgdev.behavioral.chainOfResponsibility;

public interface Handler {
    void setNextHandler(Handler handler);
    void handleRequest(Request request);
}
