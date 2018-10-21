package edu.just.stop;

public class MyThread1_5 extends Thread {

    @Override
    public void run() {

        try {
            for (int i = 0; i <11111; i++) {
                if (Thread.interrupted()) {
                    System.out.println("已经是停止状态了");

                    throw new InterruptedException();
                }
            }

            System.out.println("我是在 for 下面");

        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("最下面的语句");
        }
    }

    public static void main(String[] args) {
        MyThread1_5 thread = new MyThread1_5();
        thread.start();
        thread.interrupt();
    }
}
