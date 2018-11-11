package edu.just.queue;

import java.util.concurrent.LinkedBlockingQueue;

class LinkedService {

    private LinkedBlockingQueue<String> bq = new LinkedBlockingQueue<>(2);

    public void set() {
        int i = 0;

        while (true) {
            try {
                System.out.println(Thread.currentThread().getName()
                        +" 生产了一个 " + ++i);
                bq.add(i + "");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void get() {
        while (true) {
            try {
                String take = bq.take();
                System.out.println(Thread.currentThread().getName()
                        + " 消费了一个 " + take);
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

public class LinkedBlockingQueueTest {

    public static void main(String[] args) {
        LinkedService linkedService = new LinkedService();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                linkedService.set();
            }
        };

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                linkedService.get();
            }
        };

        Thread thread = new Thread(runnable);
//        Thread thread1 = new Thread(runnable1);
        thread.start();
//        thread1.start();
    }

}
