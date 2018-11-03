package edu.just.lo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * isLocked()：查询此锁定是否由任意线程保持
 */
class Service8 {

    public ReentrantLock lock;

    public Service8(boolean isFair) {
        lock = new ReentrantLock(isFair);
    }

    public void serviceMethod() {
        try {
            System.out.println(lock.isLocked());
            lock.lock();
            System.out.println(lock.isLocked());
        } finally {
            lock.unlock();
        }
    }

}

public class Run8 {

    public static void main(String[] args) {
        Service8 service8 = new Service8(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service8.serviceMethod();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

}
