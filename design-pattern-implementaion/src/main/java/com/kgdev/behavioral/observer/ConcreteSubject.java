package com.kgdev.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject{
    //this data when changes, observer get notified
    private Data data;
    private List<Observer> observers;
    public ConcreteSubject(){
        observers = new ArrayList<>();
    }
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer: observers){
            observer.update(data);
        }
    }
    public void dataChanged(){
        // alter data here and notify
        notifyObserver();
    }
}
