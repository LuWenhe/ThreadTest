package edu.just.syn;

class Main {
    public int i = 10;

    synchronized public void method() {
        try {
            i--;
            System.out.println("main print i = " + i);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class Sub extends Main {

    synchronized public void method2() throws InterruptedException {
        while (i > 0) {
            i--;
            System.out.println("sub print i = " + i);
            Thread.sleep(2000);
            this.method();
        }
    }

}

public class ThreadA6 extends Thread {

    @Override
    public void run() {
        Sub sub = new Sub();
        try {
            sub.method2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadA6 threadA6 = new ThreadA6();
        threadA6.start();
    }
}
