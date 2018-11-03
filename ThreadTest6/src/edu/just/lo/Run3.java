package edu.just.lo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * getWaitQueueLength(Condition condition)：返回等待与此锁定相关的给定条件 Condition。
 * 的线程估计数
 *
 * 比如同一个 Condition 对象，每个线程都执行了 Condition 对象的 await() 方法，此时调用
 * 该方法就是返回在 signal 之前的等待的线程数
 */
class Service3 {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private int count = 0;

    public void waitMethod() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 正在执行 "
                    + count++);
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
            System.out.println("是否有线程正在等待该Condition对象：" + lock.hasWaiters(condition));
            System.out.println("有 " + lock.getWaitQueueLength(condition)
                    + " 个线程正在等待 condition");
            condition.signalAll();
            System.out.println("还有 " + lock.getWaitQueueLength(condition)
                    + " 个线程正在等待");
        } finally {
            lock.unlock();
        }
    }

}

public class Run3 {

    public static void main(String[] args) throws InterruptedException {
        Service3 service3 = new Service3();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service3.waitMethod();
            }
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }

        Thread.sleep(2000);
        service3.notifyMethod();
    }

}
