package edu.test;

public class ThreadTest extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "在运行"
                    + " " + i);
        }
    }

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        threadTest.start();

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "在运行"
                + " " + i);
        }
    }
}
