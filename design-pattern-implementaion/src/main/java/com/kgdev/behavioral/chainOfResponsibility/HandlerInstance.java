package com.kgdev.behavioral.chainOfResponsibility;

public class HandlerInstance {
    public Handler getInstance(){
        return createHandlerInstance();
    }
    private Handler createHandlerInstance(){
        Handler handler1 = new Concrete1Handler();
        Handler handler2 = new Concrete2Handler();
        Handler handler3 = new Concrete2Handler();

        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);
        handler3.setNextHandler(null);
        return handler1;
    }
}
