package edu.just.lo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Run13 {

    volatile private static int nextPrintWho = 3;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    while (nextPrintWho != 1) {
                        conditionA.await();
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.println(Thread.currentThread().getName()
                                + " 执行了A " + (i + 1) + " " + nextPrintWho);
                    }
                    nextPrintWho = 2;
                    conditionB.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread threadB = new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    while (nextPrintWho != 2) {
                        conditionB.await();
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.println(Thread.currentThread().getName()
                                + " 执行了B " + (i + 1) + " " + nextPrintWho);
                    }
                    nextPrintWho = 3;
                    conditionC.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread threadC = new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    while (nextPrintWho != 3) {
                        conditionC.await();
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.println(Thread.currentThread().getName()
                                + " 执行了C " + (i + 1) + " " + nextPrintWho);
                    }
                    nextPrintWho = 1;
                    conditionA.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread thread = new Thread(threadA);
        thread.setName("AAA");
        thread.start();

        Thread thread1 = new Thread(threadB);
        thread1.setName("BBB");
        thread1.start();

        Thread thread2 = new Thread(threadC);
        thread2.setName("CCC");
        thread2.start();
    }

}
