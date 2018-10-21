package edu.just.th;

public class MyThread10 extends Thread{

    public static void main(String[] args) {
        MyThread10 thread = new MyThread10();
        System.out.println(thread.getName() + " " + thread.getId());

        Thread thread1 = Thread.currentThread();
        System.out.println(thread1.getName() + " " + thread1.getId());
    }

}
