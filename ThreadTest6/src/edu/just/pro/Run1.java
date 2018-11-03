package edu.just.pro;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyService1 {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean isFlag = false;

    public void set() {
        try {
            lock.lock();
            while (isFlag) {
                System.out.println("生产者 " + Thread.currentThread().getName() + " 在等待...");
                condition.await();
            }
            System.out.println("生产者在生产 ***");
            isFlag = true;
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
            while (!isFlag) {
                System.out.println("消费者 " + Thread.currentThread().getName() + " 在等待...");
                condition.await();
            }
            System.out.println("消费者在消费 &&&");
            isFlag = false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

class ThreadA1 extends Thread {

    private MyService1 service1;

    public ThreadA1(MyService1 service1) {
        this.service1 = service1;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            service1.set();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class ThreadB1 extends Thread {

    private MyService1 service1;

    public ThreadB1(MyService1 service1) {
        this.service1 = service1;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            service1.get();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Run1 {

    public static void main(String[] args) throws InterruptedException {
        MyService1 service1 = new MyService1();
        ThreadA1[] threadA1s = new ThreadA1[5];
        ThreadB1[] threadB1s = new ThreadB1[5];

        for (int i = 0; i < 5; i++) {
            threadA1s[i] = new ThreadA1(service1);
            threadB1s[i] = new ThreadB1(service1);

            threadA1s[i].start();
            threadB1s[i].start();
        }
    }

}
