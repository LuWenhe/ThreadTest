package edu.just.vo;

public class RunThread1 extends Thread {

    private static int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new RunThread1());
            thread.start();
        }
        Thread.sleep(200);
        System.out.println("result：" + count);
    }

}
