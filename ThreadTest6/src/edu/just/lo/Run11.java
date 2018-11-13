package edu.just.lo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock(long timeout, TimeUnit unit)：如果锁定在给定等待时间内没有被另一个线程保持，
 *                                       且当前线程未被中断，则获取该锁定
 */
class MyService11 extends ReentrantLock {

    public void waitMethod() {
        try {
            if (tryLock(2, TimeUnit.SECONDS)) {
                try {
                    System.out.println(Thread.currentThread().getName() + " 获得了锁 "
                            + System.currentTimeMillis());
                } finally {
                    Thread.sleep(6000);
                    System.out.println(Thread.currentThread().getName() + " 释放了锁 "
                            + System.currentTimeMillis());
                    unlock();
                }
                /*System.out.println(Thread.currentThread().getName() + " 获得了锁 "
                        + System.currentTimeMillis());*/
            } else {
                System.out.println(Thread.currentThread().getName() + " 没有获得锁"
                        + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

public class Run11 {

    public static void main(String[] args) throws InterruptedException {
        MyService11 service11 = new MyService11();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 调用 waitmethod "
                        + System.currentTimeMillis());
                service11.waitMethod();
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
