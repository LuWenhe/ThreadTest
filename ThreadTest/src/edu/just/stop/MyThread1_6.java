package edu.just.stop;

public class MyThread1_6 extends Thread {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("i=" + (i+1));
            }
            System.out.println("run begin");
            Thread.sleep(2000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            System.out.println("进入 catch " + this.isInterrupted());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyThread1_6 thread = new MyThread1_6();
        thread.start();
        thread.interrupt();

    }
}
