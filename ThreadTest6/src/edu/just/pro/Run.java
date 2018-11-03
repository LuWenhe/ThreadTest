package edu.just.pro;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyService {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean hasValue = true;

    public void set() {
        try {
            lock.lock();
            while (hasValue) {
                condition.await();
            }
            System.out.println("生产者 ^^^^^");
            hasValue = true;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void get() {
        try {
            lock.lock();
            while (!hasValue) {
                condition.await();
            }
            System.out.println("消费者 *****");
            hasValue = false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

class ThreadA extends Thread {

    private MyService myService;

    public ThreadA(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            myService.set();
        }
    }

}

class ThreadB extends Thread {

    private MyService myService;

    public ThreadB(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            myService.get();
        }
    }

}

public class Run {

    public static void main(String[] args) {
        MyService myService = new MyService();
        ThreadA threadA = new ThreadA(myService);
        threadA.setName("AAA");
        threadA.start();

        ThreadB threadB = new ThreadB(myService);
        threadB.setName("BBB");
        threadB.start();
    }

}
