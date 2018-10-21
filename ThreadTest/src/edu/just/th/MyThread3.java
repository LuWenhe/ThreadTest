package edu.just.th;

public class MyThread3 extends Thread {

    private int i;
    public MyThread3(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(i + " " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        MyThread3 thread1 = new MyThread3(1);
        MyThread3 thread2 = new MyThread3(2);
        MyThread3 thread3 = new MyThread3(3);
        MyThread3 thread4 = new MyThread3(4);
        MyThread3 thread5 = new MyThread3(5);
        MyThread3 thread6 = new MyThread3(6);
        MyThread3 thread7 = new MyThread3(7);
        MyThread3 thread8 = new MyThread3(8);
        MyThread3 thread9 = new MyThread3(9);
        MyThread3 thread10 = new MyThread3(10);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();
    }
}
