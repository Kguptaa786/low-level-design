package org.example;

public class Consumer implements Runnable{
    private final Buffer buffer;
    public Consumer(Buffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true){
            try {
                buffer.consume();
                Thread.sleep(100);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
