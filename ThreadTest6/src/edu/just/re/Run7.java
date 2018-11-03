package edu.just.re;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用一个Condition对象来唤醒多个等待的线程
 */
class MyService7 {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void awaitA() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " begina "
                    + System.currentTimeMillis());
            //使当前线程进入等待状态
            condition.await();
            System.out.println(Thread.currentThread().getName() + " enda "
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
            System.out.println(Thread.currentThread().getName() + " beginb "
                    + System.currentTimeMillis());
            condition.await();
            System.out.println(Thread.currentThread().getName() + " endb "
                    + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalAll() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " signalAll "
                + System.currentTimeMillis());
        condition.signalAll();
        lock.unlock();
    }

}

class ThreadA7 extends Thread {

    private MyService7 service7;

    public ThreadA7(MyService7 service7) {
        this.service7 = service7;
    }

    @Override
    public void run() {
        service7.awaitA();
    }

}

class ThreadB7 extends Thread {

    private MyService7 service7;

    public ThreadB7(MyService7 service7) {
        this.service7 = service7;
    }

    @Override
    public void run() {
        service7.awaitB();
    }

}

public class Run7 {

    public static void main(String[] args) throws InterruptedException {
        MyService7 service7 = new MyService7();
        ThreadA7 threadA7 = new ThreadA7(service7);
        threadA7.setName("AAA");
        threadA7.start();

        ThreadB7 threadB7 = new ThreadB7(service7);
        threadB7.setName("BBB");
        threadB7.start();

        Thread.sleep(2000);

        service7.signalAll();
    }

}
