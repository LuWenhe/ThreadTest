package edu.just.vo;

class MyThread extends Thread {

    volatile public static int count;

    private synchronized static void addCount() {
        for (int i = 0; i < 10; i++) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    @Override
    public void run() {
        addCount();
    }

}

public class Run2 {

    public static void main(String[] args) throws InterruptedException {
        MyThread[] myThreads = new MyThread[10];
        for (int i = 0; i < 10; i++) {
            myThreads[i] = new MyThread();
            myThreads[i].start();
        }
    }

}
