package edu.just.lo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * isHeldByCurrentThread()：查询当前线程是否保持此锁定
 */
class Service7 {

    public ReentrantLock lock;

    public Service7(boolean isFair) {
        lock = new ReentrantLock(isFair);
    }

    public void serviceMethod() {
        try {
            System.out.println(Thread.currentThread().getName() + " 是否保持此锁定："
                    + lock.isHeldByCurrentThread());
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 是否保持此锁定："
                    + lock.isHeldByCurrentThread());
        } finally {
            lock.unlock();
        }
    }
}

public class Run7 {

    public static void main(String[] args) {
        Service7 service7 = new Service7(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service7.serviceMethod();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

}
