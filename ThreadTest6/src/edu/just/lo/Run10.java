package edu.just.lo;

import java.util.concurrent.locks.ReentrantLock;

class MyService10 {

    private ReentrantLock lock = new ReentrantLock();

    public void waitMethod() {
        if (lock.tryLock()) {
            try {
                System.out.println(Thread.currentThread().getName() + " 获得了锁 " + System.currentTimeMillis());
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " 没有获得锁 " + System.currentTimeMillis());
        }
    }

}

public class Run10 {

    public static void main(String[] args) throws InterruptedException {
        MyService10 service10 = new MyService10();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service10.waitMethod();
            }
        };

        Thread thread = new Thread(runnable);
        thread.setName("AAA");
        thread.start();

        Thread.sleep(2000);

        Thread thread1 = new Thread(runnable);
        thread1.setName("BBB");
        thread1.start();
    }

}
