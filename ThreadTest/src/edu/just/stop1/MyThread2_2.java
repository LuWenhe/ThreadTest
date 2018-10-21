package edu.just.stop1;

public class MyThread2_2 extends Thread {

    synchronized public void print() {
        System.out.println("begin");
        if (Thread.currentThread().getName().equals("线程a")) {
            System.out.println("线程a永远suspend了!!!");
            Thread.currentThread().suspend();
        }
        System.out.println("end");
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread2_2 thread2_2 = new MyThread2_2();

        //创建线程 线程a
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                //在 线程a 中调用线程 Thread-0 的 print 方法
                thread2_2.print();
            }
        };
        thread1.setName("线程a");
        thread1.start();
//        Thread.sleep(2000);

        //创建线程 线程b
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                System.out.println("线程b启动了，但是进不了print方法");
                System.out.println("因为print方法被线程a锁定并且永远suspend了");
                //在 线程b 中调用线程 Thread-0 的 print 方法
                thread2_2.print();
            }
        };

        thread2.setName("线程b");
        thread2.start();

    }

}
