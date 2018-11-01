package edu.just.itl;

import java.util.Date;

class InheritableThreadLocalTest extends InheritableThreadLocal {

    @Override
    protected Object initialValue() {
        return new Date().getTime();
    }
}

class Tools {

    public static InheritableThreadLocalTest tltt = new InheritableThreadLocalTest();

}

class ThreadA extends Thread {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "中取得的值是："
                        + Tools.tltt.get());
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Run {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " 中取得的值是："
                    + Tools.tltt.get());
            Thread.sleep(2000);
        }

        ThreadA threadA = new ThreadA();
        threadA.start();
    }

}
