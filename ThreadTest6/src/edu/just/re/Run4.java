package edu.just.re;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyService4 {

    private Lock lock = new ReentrantLock();
    //使用 lock 对象创建一个 Condition 实例，即对象监视器
    private Condition condition = lock.newCondition();

    public void await() {
        //使用 await() 之前一定要使用 lock() 获取对象锁
        lock.lock();

        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class ThreadA4 extends Thread {

    private MyService4 service4;

    public ThreadA4(MyService4 service4) {
        this.service4 = service4;
    }

    @Override
    public void run() {
        service4.await();
    }
}

public class Run4 {

    public static void main(String[] args) {
        MyService4 service4 = new MyService4();
        ThreadA4 threadA4 = new ThreadA4(service4);
        threadA4.start();
    }

}
