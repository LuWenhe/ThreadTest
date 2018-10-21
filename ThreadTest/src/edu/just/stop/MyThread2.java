package edu.just.stop;

/**
 * stop()：暴力停止线程
 */
public class MyThread2 extends Thread {

    private int i = 0;

    /*@Override
    public void run() {
        try {
            while (true) {
                i++;
                System.out.println("i=" + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void run() {
        while (true) {
            i++;
            System.out.println("i=" + i);
        }
    }

    public static void main(String[] args) {
        MyThread2 thread = new MyThread2();
        thread.start();

    }
}
