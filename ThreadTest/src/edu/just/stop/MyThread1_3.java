package edu.just.stop;

public class MyThread1_3 extends Thread {

    @Override
    public void run() {
        System.out.println("run: " + this.isInterrupted());
        for (int i = 0; i < 50000; i++) {
            System.out.println("i=" + (i + 1));
            if (this.isInterrupted()) {
                System.out.println("已经是停止状态！！！");
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread1_3 thread = new MyThread1_3();
        thread.start();
        thread.interrupt();
        System.out.println("main: " + thread.isInterrupted());
        System.out.println("end");
    }
}
