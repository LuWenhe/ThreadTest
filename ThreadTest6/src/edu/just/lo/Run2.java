package edu.just.lo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * getQueueLength()：返回正在等待获取此锁定的线程的估计数，比如等待 lock 锁的释放
 */
class Service2 {

    public ReentrantLock lock = new ReentrantLock();

    public void serviceMethod() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " serviceMethod");
            System.out.println(Thread.currentThread().getName() + " 正在等待获取 lock 锁定的个数是："
                    + lock.getQueueLength());
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

public class Run2 {

    public static void main(String[] args) throws InterruptedException {
        Service2 service2 = new Service2();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service2.serviceMethod();
            }
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }

        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName()
                + " 正在等待获取 lock 锁定的个数是：" + service2.lock.getQueueLength());
    }

}
