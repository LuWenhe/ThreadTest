package edu.just.pri;

import java.util.Random;

/**
 * 高优先级的线程总是大部分先执行完，但不代表高优先级的线程全部先执行完
 * 同时线程执行顺序和代码块的执行顺序无关，只和有优先级有关
 */
class MyThread1_1 extends Thread {
    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        long addResult = 0;
        for (int i = 0; i < 5000; i++) {
            Random random = new Random();
            random.nextInt();
            addResult = addResult + 1;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("thread 1_1 use time = " + endTime);
    }
}

public class MyThread1 extends Thread {

    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        long addResult = 0;
        for (int i = 0; i < 5000; i++) {
            Random random = new Random();
            random.nextInt();
            addResult = addResult + 1;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("thread 1 use time = " + endTime);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            //创建线程 Thread1_1
            MyThread1_1 thread1_1 = new MyThread1_1();
            //为线程 Thread1_1 设置优先级 2
            thread1_1.setPriority(3);
            thread1_1.start();

            //创建线程 Thread1
            MyThread1 thread1 = new MyThread1();
            //为线程 Thread1 设置优先级为 10
            thread1.setPriority(10);
            thread1.start();

        }
    }
}
