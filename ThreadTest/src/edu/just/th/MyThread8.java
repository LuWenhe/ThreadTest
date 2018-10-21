package edu.just.th;

/**
 * isAlive()：判断当前线程是否处于活动状态
 */
public class MyThread8 extends Thread {

    @Override
    public void run() {
        System.out.println("run = " + this.isAlive());
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread8 thread = new MyThread8();
        System.out.println("begin == " + thread.isAlive());
        thread.start();
        Thread.sleep(3000);
        System.out.println("end == " + thread.isAlive());
    }
}
