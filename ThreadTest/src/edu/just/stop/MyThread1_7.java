package edu.just.stop;

public class MyThread1_7 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + (i + 1));
        }
    }

    public static void main(String[] args) {
        MyThread1_7 thread = new MyThread1_7();
        thread.start();
        thread.interrupt();

        //测试当前线程是否中断，此时当前线程是 main
        System.out.println("当前线程是：" + Thread.currentThread().getName());
        System.out.println("停止的线程是：" + thread.getName());
        /*System.out.println("interrupted 1：" + thread.interrupted());
        System.out.println("interrupted 2：" + thread.interrupted());*/

        System.out.println("isInterrupted 1：" + thread.isInterrupted());
        System.out.println("isInterrupted 2：" + thread.isInterrupted());
    }
}
