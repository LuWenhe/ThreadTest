package edu.just.th;

public class MyThread extends Thread{

    @Override
    public void run() {
        System.out.println("MyThread");
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("运行结束");
    }
}