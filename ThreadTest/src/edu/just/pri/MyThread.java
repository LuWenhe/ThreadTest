package edu.just.pri;

class MyThread0_1 extends Thread {
    @Override
    public void run() {
        //因为线程 Thread1 是由线程 Thread0 调用，因此 Threa1 的优先级和 Thread0 一样，都是 8
        System.out.println("MyThread0_1 run priority = " + this.getPriority());
    }
}

/**
 * 线程的优先级具有继承性，比如 A 线程启动 B 线程，那么 B 线程的优先级和 A 线程是一样的
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        //因为线程 Thread0 是由线程 main 调用的，因此 Thread0 的优先级和 main 的优先级一样
        System.out.println("MyThread0 run priority = " + this.getPriority());
        //创建线程 MyThread0_1
        MyThread0_1 myThread0_1 = new MyThread0_1();
        //为线程 MyThread0_1 设置优先级为 8
        myThread0_1.setPriority(8);
        //在线程 Thread0 里面启动线程 MyThread0_1
        myThread0_1.start();
    }

    public static void main(String[] args) {
        System.out.println("main thread begin priority = " +
                Thread.currentThread().getPriority());
        //为线程 main 设置优先级为 6
        Thread.currentThread().setPriority(6);
        System.out.println("main thread end priority = " +
                Thread.currentThread().getPriority());
        //创建线程 Thread0
        MyThread thread = new MyThread();
        //在线程 main 里面启动线程 Thread0
        thread.start();
    }

}
