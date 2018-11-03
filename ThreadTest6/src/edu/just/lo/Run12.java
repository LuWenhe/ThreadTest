package edu.just.lo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class MyService12 {

    public ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    /*public void testMethod() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " wait begin");
            condition.await();
            System.out.println(Thread.currentThread().getName() + " wait end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }*/

    public void testMethod1() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " begin");
            condition.awaitUninterruptibly();
            System.out.println(Thread.currentThread().getName() + " end");
        } finally {
            lock.unlock();
        }
    }

}

class ThreadA extends Thread {

    private MyService12 service12;

    public ThreadA(MyService12 service12) {
        this.service12 = service12;
    }

    @Override
    public void run() {
        service12.testMethod1();
    }
}

public class Run12 {

    public static void main(String[] args) throws InterruptedException {
        MyService12 service12 = new MyService12();
        ThreadA threadA = new ThreadA(service12);
        threadA.start();
        Thread.sleep(2000);
        threadA.interrupt();
    }

}
