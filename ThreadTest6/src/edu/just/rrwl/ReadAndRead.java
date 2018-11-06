package edu.just.rrwl;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock 的多个读锁之间是共享的
 */
class MyService {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " 获得读锁 "
                    + System.currentTimeMillis());
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
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
        myService.read();
    }

}

class ThreadB extends Thread {

    private MyService myService;

    public ThreadB(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.read();
    }

}

public class ReadAndRead {

    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        ThreadA threadA = new ThreadA(service);
        threadA.setName("AAAA");
        threadA.start();

        ThreadB threadB = new ThreadB(service);
        threadB.setName("BBBB");
        threadB.start();
    }

}
