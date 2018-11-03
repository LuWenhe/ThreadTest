package edu.just.re;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyService {

    private Lock lock = new ReentrantLock();

    public void testMethod() {
        //调用 ReentrantLock 对象的 lock() 方法获取锁
        lock.lock();

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " " + (i + 1));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //调用 unlock() 方法释放锁
        lock.unlock();
    }

}

class MyThread extends Thread {

    private MyService myService;

    public MyThread(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethod();
    }

}

public class Run {

    public static void main(String[] args) {
        MyService myService = new MyService();
        MyThread myThread = new MyThread(myService);
        MyThread myThread1 = new MyThread(myService);
        MyThread myThread2 = new MyThread(myService);
        MyThread myThread3 = new MyThread(myService);
        MyThread myThread4 = new MyThread(myService);

        myThread.start();
        myThread1.start();
        myThread2.start();
        myThread3.start();
        myThread4.start();
    }

}
