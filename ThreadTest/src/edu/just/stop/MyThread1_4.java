package edu.just.stop;

public class MyThread1_4 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 3000; i++) {
            if (Thread.interrupted()) {
                System.out.println("已经是停止状态了!!");
                break;
            }
            System.out.println("i = " + i);
        }
        System.out.println("继续输出");
    }

    public static void main(String[] args) {
        MyThread1_4 thread = new MyThread1_4();
        thread.start();
        thread.interrupt();

    }
}
