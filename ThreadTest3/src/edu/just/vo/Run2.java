package edu.just.vo;

class MyThread extends Thread {

    volatile public static int count;

    private static void addCount() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    @Override
    public void run() {
        addCount();
    }

}

public class Run2 {

    public static void main(String[] args) {
        MyThread[] myThreads = new MyThread[100];
        for (int i = 0; i < 10; i++) {
            myThreads[i] = new MyThread();
        }

        for (int i = 0; i < 10; i++) {
            myThreads[i].start();
        }
    }

}
