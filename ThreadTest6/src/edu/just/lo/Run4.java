package edu.just.lo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * hasQueuedThread(Thread thread)：查询指定的线程是否正在等待获取此锁定，lock 对象
 * hasQueuedThreads()：查询是否有线程正在等待获取此锁定
 * hasWaiters(Condition condition)：查询是否有线程正在等待与此锁定相关的 condition 条件
 */
class Service4 {

    public ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public void waitMethod() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 正在执行");
            System.out.println("查询是否有线程正在等待与此锁定相关的Condition：" + lock.hasWaiters(condition));
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}

public class Run4 {

    public static void main(String[] args) throws InterruptedException {
        Service4 service4 = new Service4();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service4.waitMethod();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(2000);
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread.sleep(2000);

        System.out.println(thread.getName() + "是否正在等待获取此锁定："
                + service4.lock.hasQueuedThread(thread));
        System.out.println(thread1.getName() + "是否正在等待获取此锁定："
                + service4.lock.hasQueuedThread(thread1));
        System.out.println("是否有线程正在等待获取此锁定：" + service4.lock.hasQueuedThreads());
    }

}
