package edu.just.rrwl;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock 的读锁和写锁是互斥的
 */
class MyService3 {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " 获得读锁 "
                    + System.currentTimeMillis());
            Thread.sleep(10000);
            System.out.println(Thread.currentThread().getName() + " 结束读锁 "
                    + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

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

class ThreadA3 extends Thread {

    private MyService3 myService3;

    public ThreadA3(MyService3 myService3) {
        this.myService3 = myService3;
    }

    @Override
    public void run() {
//        myService3.read();
        myService3.write();
    }

}

class ThreadB3 extends Thread {

    private MyService3 myService3;

    public ThreadB3(MyService3 myService3) {
        this.myService3 = myService3;
    }

    @Override
    public void run() {
//        myService3.write();
        myService3.read();
    }
}

public class ReadAndWrite {

    public static void main(String[] args) {
        MyService3 service3 = new MyService3();
        ThreadA3 threadA3 = new ThreadA3(service3);
        threadA3.setName("AAA");
        ThreadB3 threadB3 = new ThreadB3(service3);
        threadB3.setName("BBB");

        threadA3.start();
        threadB3.start();
    }

}
