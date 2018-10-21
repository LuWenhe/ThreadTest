package edu.just.pri;

import java.util.Random;

class MyThread2_1 extends Thread {
    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Random random = new Random();
            random.nextInt();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("thread 2_1 use time = " + (endTime - beginTime));

    }
}

public class MyThread2 extends Thread {

    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Random random = new Random();
            random.nextInt();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("thread 2 use time = " + (endTime - beginTime));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            MyThread2 myThread2 = new MyThread2();
            myThread2.setPriority(5);
            myThread2.start();

            MyThread2_1 myThread2_1 = new MyThread2_1();
            myThread2_1.setPriority(6);
            myThread2_1.start();
        }
    }

}
