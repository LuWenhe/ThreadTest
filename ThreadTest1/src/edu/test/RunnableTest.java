package edu.test;

public class RunnableTest implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("run 在运行");
        }
    }

    public static void main(String[] args) {
        RunnableTest test = new RunnableTest();
        Thread thread = new Thread(test);
        thread.start();

        for (int i = 0; i < 5; i++) {
            System.out.println("main 在运行");
        }
    }
}
