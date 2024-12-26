package com.kgdev.behavioral.chainOfResponsibility;

public class Concrete3Handler implements  Handler{
    private Handler nextHandler;
    @Override
    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleRequest(Request request) {
        //if possible process else pass to next
        // this is last handle so throw some error or message to the client that request cannot be process
    }
}
