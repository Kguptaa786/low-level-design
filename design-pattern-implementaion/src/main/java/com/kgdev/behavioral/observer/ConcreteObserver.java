package com.kgdev.behavioral.observer;

public class ConcreteObserver implements Observer{
    private Data data;
    private Subject subject;
    public ConcreteObserver(Subject subject){
        subject = this.subject;
        subject.registerObserver(this);
    }
    @Override
    public void update(Data data) {
        this.data = data;
    }
}
