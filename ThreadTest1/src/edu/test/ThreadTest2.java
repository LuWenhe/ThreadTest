package edu.test;

public class ThreadTest2 extends Thread {

    private void print() {
        Thread thread = Thread.currentThread();
        String name = thread.getName();
        String name1 = this.getName();
        System.out.println("name: " + name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            //run 方法中的线程
            print();
        }
    }

    public static void main(String[] args) {
        ThreadTest2 threadTest2 = new ThreadTest2();
        threadTest2.setName("lu");
        threadTest2.start();

        //main 方法的线程
        for (int i = 0; i < 10; i++) {
            threadTest2.print();
        }
    }
}
