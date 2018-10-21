package edu.just.run;

public class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("运行中");
    }

    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("运行结束");
    }

}
