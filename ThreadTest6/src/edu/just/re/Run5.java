package edu.just.re;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyService5 {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void waitMethod() {
        try {
            lock.lock();
            System.out.println("wait begin");
            //使当前所在的线程进入等待 waiting 状态
            condition.await();
            System.out.println("wait end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("锁释放了!");
        }
    }
}

class MyThreadA6 extends Thread {

    private MyService5 service5;

    public MyThreadA6(MyService5 service5) {
        this.service5 = service5;
    }

    @Override
    public void run() {
        service5.waitMethod();
    }
}

class MyThreadB6 extends Thread {

    private MyService5 service5;

    public MyThreadB6(MyService5 service5) {
        this.service5 = service5;
    }

    @Override
    public void run() {
        service5.waitMethod();
    }
}

public class Run5 {

    public static void main(String[] args) {
        MyService5 service5 = new MyService5();
        MyThreadA6 threadA6 = new MyThreadA6(service5);
        threadA6.start();


    }

}
