package edu.just.lo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * hasWaiters(Condition condition)：查询是否有线程正在等待与此锁定有关的 condition 条件
 */
class Service5 {

    public ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public void awaitMethod() {
        try {
            lock.lock();
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void notifyMethod() {
        try {
            lock.lock();
            System.out.println("有没有线程正在等待 condition？"
                    + lock.hasWaiters(condition) + " 线程数是多少？"
                    + lock.getWaitQueueLength(condition));
            condition.signal();
            System.out.println(lock.getWaitQueueLength(condition));
        } finally {
            lock.unlock();
        }
    }

}

public class Run5 {

    public static void main(String[] args) throws InterruptedException {
        Service5 service5 = new Service5();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service5.awaitMethod();
            }
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }

        Thread.sleep(2000);
        service5.notifyMethod();
    }

}
