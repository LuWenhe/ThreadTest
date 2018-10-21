package edu.just.th;

public class MyThread2 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int time = (int) (Math.random()*1000);
            try {
                Thread.sleep(time);
                System.out.println("run=" + Thread.currentThread().getName() + " i1=" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyThread2 thread2 = new MyThread2();
        thread2.run();
        for (int i = 0; i < 10; i++) {
            int time = (int) (Math.random()*1000);
            try {
                Thread.sleep(time);
                System.out.println("main=" + Thread.currentThread().getName() + " i2=" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
