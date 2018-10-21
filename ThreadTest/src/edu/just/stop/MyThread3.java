package edu.just.stop;

public class MyThread3 extends Thread {

    @Override
    public void run() {
        while (true) {
            if (this.isInterrupted()) {
                System.out.println("停止了!");
                return;
            }
            System.out.println("timer=" + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        MyThread3 thread = new MyThread3();
        thread.start();
        thread.interrupt();
    }
}
