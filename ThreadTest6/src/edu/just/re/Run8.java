package edu.just.re;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Service8 {

    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();

    public void awaitA() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " begin awaitA "
                    + System.currentTimeMillis());
            conditionA.await();
            System.out.println(Thread.currentThread().getName() + " end awaitA "
                    + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void awaitB() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " begin awaitB "
                    + System.currentTimeMillis());
            conditionB.await();
            System.out.println(Thread.currentThread().getName() + " end awaitB "
                    + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalAll_A() {
        try {
            lock.lock();
            System.out.println(ThreadB.currentThread().getName() + " signallA "
                    + System.currentTimeMillis());
            conditionA.signal();
        } finally {
            lock.unlock();
        }
    }

    public void signalAll_B() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " signalB "
                    + System.currentTimeMillis());
            conditionB.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

class ThreadA8 extends Thread {

    private Service8 service8;

    public ThreadA8(Service8 service8) {
        this.service8 = service8;
    }

    @Override
    public void run() {
        service8.awaitA();
    }

}

class ThreadB8 extends Thread {

    private Service8 service8;

    public ThreadB8(Service8 service8) {
        this.service8 = service8;
    }

    @Override
    public void run() {
        service8.awaitB();
    }
}

public class Run8 {

    public static void main(String[] args) throws InterruptedException {
        Service8 service8 = new Service8();
        ThreadA8 threadA8 = new ThreadA8(service8);
        threadA8.setName("AAA");
        threadA8.start();

        ThreadB8 threadB8 = new ThreadB8(service8);
        threadB8.setName("BBB");
        threadB8.start();

        Thread.sleep(2000);

//        service8.signalAll_A();
        service8.signalAll_B();
    }

}
