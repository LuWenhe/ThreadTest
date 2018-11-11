package edu.just.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class ArrayService {

    private BlockingQueue<String> bq = new ArrayBlockingQueue<>(10);

    public void set() {
        int i = 0;

        while (true) {
            try {
                System.out.println(Thread.currentThread().getName()
                        +" 我生产了一个 " + ++i);
                bq.put(i + "");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void get() {
        while (true) {
            try {
                System.out.println(Thread.currentThread().getName()
                        + " 我消费了一个 " + bq.take());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

public class ArrayBlockingQueueTest {

    public static void main(String[] args) {
        ArrayService service = new ArrayService();

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                service.set();
            }
        };

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                service.get();
            }
        };

        Thread producerThread = new Thread(runnable);
        Thread customerThread = new Thread(runnable1);
        producerThread.start();
        customerThread.start();
    }

}
