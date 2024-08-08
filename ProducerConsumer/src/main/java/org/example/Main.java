package org.example;

public class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5);  // Buffer with capacity 5
        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread = new Thread(new Consumer(buffer));

        producerThread.start();
        consumerThread.start();


    }


}