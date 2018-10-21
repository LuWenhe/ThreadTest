package edu.just.stop;

/**
 * interrupr()：此方法不会终止一个正在运行的线程
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            System.out.println("i=" + (i + 1));
        }
    }

    public static void main(String[] args) {
        try {
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(2000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
