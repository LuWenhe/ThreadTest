package edu.just.lo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock()：在调用try()方法的时候，如果锁没有被另外一个线程持有，
 *            那么就返回true，否则返回false
 */
class MyService10 {

    public ReentrantLock lock = new ReentrantLock();
    private boolean tryLock;

    public void waitMethod() {
        lock.lock();

        tryLock = lock.tryLock();
        if (tryLock) {
            System.out.println("该lock锁有没有被另一个线程保持：" + tryLock);
            System.out.println(Thread.currentThread().getName() + " 获得锁 ");
        } else {
            System.out.println("该lock锁有没有被另一个线程保持：" + tryLock);
            System.out.println(Thread.currentThread().getName() + " 没有获得锁 ");
        }

        lock.unlock();
    }

    /*public void waitMethod1() {
        if (tryLock) {
            System.out.println(Thread.currentThread().getName() + " 获得了锁 ");
        } else {
            System.out.println(Thread.currentThread().getName() + " 没有获得锁");
        }
    }*/

}

public class Run10 {

    public static void main(String[] args) {
        MyService10 service10 = new MyService10();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service10.waitMethod();
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
