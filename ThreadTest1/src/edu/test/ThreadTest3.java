package edu.test;

public class ThreadTest3 extends Thread {

    private void printMsg() {
        Thread thread = Thread.currentThread();
        String name = thread.getName();
        System.out.println("name: " + name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            printMsg();
        }
    }

    public static void main(String[] args) {
        ThreadTest3 threadTest3 = new ThreadTest3();
        System.out.println("调用 start 方法之前，t.isAlive()=" + threadTest3.isAlive());
        threadTest3.start();
        System.out.println("调用 start 方法之后，t.isAlive()=" + threadTest3.isAlive());

        for (int i = 0; i < 10; i++) {
            threadTest3.printMsg();
        }
        System.out.println("main() 方法结束时，t.isAlive()=" + threadTest3.isAlive());
    }
}
