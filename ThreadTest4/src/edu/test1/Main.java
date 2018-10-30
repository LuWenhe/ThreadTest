package edu.test1;

class ThreadTest extends Thread {

    private String name;

    public ThreadTest(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + " - " + i);
        }
    }
}

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ThreadTest t1 = new ThreadTest("AAA");
        ThreadTest t2 = new ThreadTest("BBB");
        t1.start();
        t1.join();
        t2.start();
    }

}
