package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    private final int capacity;
    private final Queue<Integer> queue;

    public Buffer(int capacity){
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public void produce(int item) throws InterruptedException {
        synchronized (this){
            while(queue.size() == capacity){
                wait();
            }
            queue.add(item);
            System.out.println("Produced: "+item);
            notifyAll();
        }
    }

    public int consume() throws InterruptedException {
        synchronized (this){
            while (queue.isEmpty()){
                wait();
            }
            int item = queue.remove();
            System.out.println("Consumed: "+item);
            notifyAll();
            return item;
        }
    }
}
