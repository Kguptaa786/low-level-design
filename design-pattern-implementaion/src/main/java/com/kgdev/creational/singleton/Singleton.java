package com.kgdev.creational.singleton;

import java.io.Serial;
import java.io.Serializable;

public class Singleton implements Cloneable, Serializable {
    private volatile static Singleton singletonObject;

    private Singleton(){
        throw new IllegalArgumentException("Object already created.");
    }

    public static Singleton getInstance(){
        if(singletonObject == null){
            synchronized (Singleton.class){
                if (singletonObject == null){
                    singletonObject = new Singleton();
                }
            }
        }
        return singletonObject;
    }

    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    @Serial
    protected Object readResolve(){
        return singletonObject;
    }

    public void doSomeWork(){
        // write your code
    }
}
