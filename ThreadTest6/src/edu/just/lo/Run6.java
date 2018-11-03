package edu.just.lo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * isFair()：判断当前的 lock 锁是不是公平锁
 */
class Service6 {

    public ReentrantLock lock;

    public Service6() {
        lock = new ReentrantLock();
    }

    public Service6(boolean isFair) {
        lock = new ReentrantLock(isFair);
    }

    public void serviceMethod() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()
                    + " 公平锁的情况：" + lock.isFair());
        } finally {
            lock.unlock();
        }
    }

}

public class Run6 {

    public static void main(String[] args) {
        Service6 service6 = new Service6(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service6.serviceMethod();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        Service6 service61 = new Service6(false);
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                service61.serviceMethod();
            }
        };

        thread = new Thread(runnable1);
        thread.start();

        Service6 service62 = new Service6();
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                service62.serviceMethod();
            }
        };

        thread = new Thread(runnable2);
        thread.start();
    }

}
