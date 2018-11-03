package edu.just.rrwl;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock 的写锁与写锁之间是互斥的
 */
class MyService2 {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void write() {
        try {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " 获得写锁 "
                    + System.currentTimeMillis());
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName() + " 结束写锁 "
                    + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

}

class ThreadA2 extends Thread {

    private MyService2 service2;

    public ThreadA2(MyService2 service2) {
        this.service2 = service2;
    }

    @Override
    public void run() {
        service2.write();
    }

}

class ThreadB2 extends Thread {

    private MyService2 service2;

    public ThreadB2(MyService2 service2) {
        this.service2 = service2;
    }

    @Override
    public void run() {
        service2.write();
    }
}

public class WriteAndWrite {

    public static void main(String[] args) {
        MyService2 service2 = new MyService2();
        ThreadA2 threadA2 = new ThreadA2(service2);
        threadA2.setName("AAA");
        ThreadB2 threadB2 = new ThreadB2(service2);
        threadB2.setName("BBB");

        threadA2.start();
        threadB2.start();
    }

}
