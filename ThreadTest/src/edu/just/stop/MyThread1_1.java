package edu.just.stop;

public class MyThread1_1 extends Thread {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        Thread.currentThread().interrupt();
        /*System.out.println("是否停止 1 ? = " + Thread.interrupted());
        System.out.println("是否停止 2 ? = " + Thread.interrupted());*/

        System.out.println("是否停止 1 = " + Thread.currentThread().isInterrupted());
        System.out.println("是否停止 1 = " + Thread.currentThread().isInterrupted());

        System.out.println("end!");
    }

}
