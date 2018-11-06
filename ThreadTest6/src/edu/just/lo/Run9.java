package edu.just.lo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * lockInterruptibly()：如果当前线程未被中断，则获取锁定，如果已经被中断则出现异常
 */
class MyService9 {

    public ReentrantLock lock = new ReentrantLock();

    public void waitMethod() {
        try {
            lock.lockInterruptibly();
            System.out.println("lock begin：" + Thread.currentThread().getName()
                    + " " + System.currentTimeMillis());
            for (int i = 0; i < 10; i++) {

            }
            System.out.println("lock end：" + Thread.currentThread().getName()
                    + " " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

}

public class Run9 {

    public static void main(String[] args) throws InterruptedException {
        MyService9 service9 = new MyService9();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service9.waitMethod();
            }
        };
        Thread thread = new Thread(runnable);
        thread.setName("AAA");
        thread.start();
        Thread.sleep(2000);

        Thread thread1 = new Thread(runnable);
        thread1.setName("BBB");
        thread1.start();

        thread1.interrupt();            //做个标记
        System.out.println("main end");
    }

}
