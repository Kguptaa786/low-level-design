package org.example;

public class Producer implements Runnable{

    private final Buffer buffer;
    public Producer(Buffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int item = 0;
        while (true){
            try {
                buffer.produce(item++);
                if(item == 10){
                    break;
                }
                Thread.sleep(5000);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
