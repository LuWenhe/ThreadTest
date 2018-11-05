package edu.just.lo;

import java.util.concurrent.locks.ReentrantLock;

class Service11 {

    public ReentrantLock lock = new ReentrantLock();
    private boolean tryLock;

    public void waitMethod() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 进入lock " + System.currentTimeMillis());
            tryLock = lock.tryLock();

            if (tryLock) {
                System.out.println(Thread.currentThread().getName() + " 获得锁 " + System.currentTimeMillis());
            } else {
                System.out.println(Thread.currentThread().getName() + " 没有获得锁 " + System.currentTimeMillis());
            }
        } finally {
            System.out.println(Thread.currentThread().getName() + " finally " + System.currentTimeMillis());
            lock.unlock();
            lock.unlock();
        }
    }

}

public class Run10_1 {

    public static void main(String[] args) throws InterruptedException {
        Service11 service11 = new Service11();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service11.waitMethod();
            }
        };

        Thread thread = new Thread(runnable);
        thread.setName("AAA");
        thread.start();

        Thread thread1 = new Thread(runnable);
        thread1.setName("BBB");
        thread1.start();
    }

}
