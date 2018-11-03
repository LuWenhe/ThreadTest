package edu.just.lo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class MyService11 extends ReentrantLock {

    public void waitMethod() {
        try {
            if (tryLock(5, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + " 获得了锁 "
                        + System.currentTimeMillis());
                Thread.sleep(1000);
            } else {
                System.out.println(Thread.currentThread().getName() + " 没有获得锁"
                        + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

public class Run11 {

    public static void main(String[] args) {
        MyService11 service11 = new MyService11();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " 调用 waitmehotd "
                        + System.currentTimeMillis());
                service11.waitMethod();
            }
        };

        Thread thread = new Thread(runnable);
        thread.setName("AAA");
        Thread thread1 = new Thread(runnable);
        thread1.setName("BBB");
        thread.start();
        thread1.start();
    }

}
