package edu.just.tl;

import java.util.Date;

class Tools3 {

    public static ThreadLocal<Date> threadLocal = new ThreadLocal<>();

}

class ThreadA3 extends Thread {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                if (Tools3.threadLocal.get() == null) {
                    System.out.println("时间是：" + new Date().getTime());
                    Tools3.threadLocal.set(new Date());
                }

                System.out.println(Thread.currentThread().getName() + " "
                        + Tools3.threadLocal.get().getTime());
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadB3 extends Thread {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                if (Tools3.threadLocal.get() == null) {
                    System.out.println("时间是：" + new Date().getTime());
                    Tools3.threadLocal.set(new Date());
                }

                System.out.println(Thread.currentThread().getName() + " "
                        + Tools3.threadLocal.get().getTime());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Run3 {

    /**
     * ThreadLocal 类第一次的 get() 方法的返回值为 null
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadA3 threadA3 = new ThreadA3();
        threadA3.setName("AAA");
        threadA3.start();
        Thread.sleep(2000);
        ThreadB3 threadB3 = new ThreadB3();
        threadB3.setName("BBB");
        threadB3.start();
    }

}
