package edu.just.vo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用原子类 AtomicInteger
 */
class AddCountThread extends Thread {

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() +
                    " " + count.incrementAndGet());
        }
    }
}

public class Run3 {

    public static void main(String[] args) {
        AddCountThread addCountThread = new AddCountThread();
        Thread thread = new Thread(addCountThread);
        thread.start();

        Thread thread1 = new Thread(addCountThread);
        thread1.start();

        Thread thread2 = new Thread(addCountThread);
        thread2.start();

        Thread thread3 = new Thread(addCountThread);
        thread3.start();

        Thread thread4 = new Thread(addCountThread);
        thread4.start();

        Thread thread5 = new Thread(addCountThread);
        thread5.start();
    }

}
