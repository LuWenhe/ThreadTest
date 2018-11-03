package edu.just.pro;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁：表示线程获取锁的顺序是按照线程加锁的顺序来分配的，
 *        即先执行 lock() 方法的线程先被唤醒
 */
class FairService {

    private Lock lock;

    public FairService(boolean isFair) {
        lock = new ReentrantLock(isFair);
    }

    public void serviceMethod() {
        try {
            System.out.println(Thread.currentThread().getName() + " 加锁了");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 获得锁");
        } finally {
            lock.unlock();
        }
    }

}

public class FairTest {

    public static void main(String[] args) {
        FairService service = new FairService(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service.serviceMethod();
            }
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }

    }

}
