package com.kgdev.behavioral.chainOfResponsibility;

public class Concrete1Handler implements Handler {
    private Handler nextHandler;
    @Override
    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleRequest(Request request) {
        //if possible process else pass to next
        nextHandler.handleRequest(request);
    }
}
