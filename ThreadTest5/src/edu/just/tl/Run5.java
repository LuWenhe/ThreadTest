package edu.just.tl;

import java.util.Date;

class ThreadLocalExt extends ThreadLocal {

    @Override
    protected Object initialValue() {
        return new Date().getTime();
    }
}

class Tools5 {

     static ThreadLocalExt threadLocalExt = new ThreadLocalExt();

}

class ThreadA5 extends Thread {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "中的值是："
                        + Tools5.threadLocalExt.get());
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Run5 {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "中取值为："
                    + Tools5.threadLocalExt.get());
            Thread.sleep(2000);
        }

//        Thread.sleep(2000);
        ThreadA5 threadA5 = new ThreadA5();
        threadA5.start();
    }

}
