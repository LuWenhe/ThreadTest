package edu.just.stop1;

/**
 * yield 方法
 */
public class MyThread3 extends Thread {

    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 100000; i++) {
            Thread.yield();
            count = count + (i + 1);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("count：" + count);
        System.out.println("用时：" + (endTime - beginTime) + " ms! ");
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread3 thread = new MyThread3();
        thread.start();
    }
}
