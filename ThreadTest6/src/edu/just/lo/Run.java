package edu.just.lo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * getHoldCount()：查询当前线程保持此锁定的个数，即调用 lock() 方法的次数
 */
class Service {

    private ReentrantLock lock = new ReentrantLock();

    public void serviceMethod() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " method1 getHoldCount："
                    + lock.getHoldCount());
            serviceMethod2();
        } finally {
            lock.unlock();
        }
    }

    public void serviceMethod2() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " method2 getHoldCount："
                    + lock.getHoldCount());
        } finally {
            lock.unlock();
        }
    }

}

public class Run {

    public static void main(String[] args) {
        Service service = new Service();
        service.serviceMethod();
    }

}
