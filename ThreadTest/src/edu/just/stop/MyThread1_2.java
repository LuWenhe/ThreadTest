package edu.just.stop;

public class MyThread1_2 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            System.out.println("i = " + (i + 1));
        }
    }

    public static void main(String[] args) {
        MyThread1_2 thread = new MyThread1_2();
        thread.start();

        thread.interrupt();
        System.out.println("是否停止 1：" + thread.isInterrupted());
        System.out.println("是否停止 1：" + thread.isInterrupted());

        System.out.println("end");
    }

}
