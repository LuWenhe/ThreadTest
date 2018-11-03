package edu.just.re;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReetrantLock 持有的是对象监视器
 */
class MyService2 {

    private Lock lock = new ReentrantLock();

    public void methodA() {
        //当前线程获得对象锁
        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName() + " begin "
                    + System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " end "
                    + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //当前线程释放锁
        lock.unlock();
    }

    public void methodB() {
        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName() + " begin "
                    + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " end "
                    + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.unlock();
    }

}

class ThreadA extends Thread {

    private MyService2 service2;

    public ThreadA(MyService2 service2) {
        this.service2 = service2;
    }

    @Override
    public void run() {
        service2.methodA();
    }

}

class ThreadB extends Thread {

    private MyService2 service2;

    public ThreadB(MyService2 service2) {
        this.service2 = service2;
    }

    @Override
    public void run() {
        service2.methodB();
    }

}

public class Run2 {

    public static void main(String[] args) throws InterruptedException {
        MyService2 myService2 = new MyService2();
        ThreadA threadA = new ThreadA(myService2);
        ThreadB threadB = new ThreadB(myService2);

        threadA.start();
        Thread.sleep(2000);
        threadB.start();
    }

}
