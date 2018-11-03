package edu.just.re;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyService3 {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void await() {
//        lock.lock();

        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class ThreadA3 extends Thread {

    private MyService3 service3;

    public ThreadA3(MyService3 service3) {
        this.service3 = service3;
    }

    @Override
    public void run() {
        service3.await();
    }

}

public class Run3 {

    public static void main(String[] args) {
        MyService3 service3 = new MyService3();
        ThreadA3 threadA3 = new ThreadA3(service3);
        threadA3.start();
    }

}
