package edu.just.stop;

/**
 * this.interrupted()：
 * 测试当前线程是否已经中断，当前线程指的是运行 this.interrupted() 方法的线程
 * 执行后将具有将状态标志清除为 false 的功能
 *
 * this.isInterreupted()：
 * 测试线程是否已经中断，但不清楚状态标志
 */
public class MyThread1 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("i = " + (i + 1));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread1 thread1 = new MyThread1();
        thread1.start();
        Thread.sleep(2000);
        //当前线程是 main 线程，因此
        thread1.interrupt();
        System.out.println("name: " + thread1.getName());
        System.out.println("name: " + Thread.currentThread().getName());
        System.out.println("是否停止：" + Thread.interrupted());
        System.out.println("是否停止：" + Thread.interrupted());

//        如果使用 Thread.sleep 方法，那么中断状态将会清除
        System.out.println("是否存活：" + thread1.isAlive());
        System.out.println("1: " + thread1.isInterrupted());

        System.out.println("end");
    }

}
