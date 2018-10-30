package edu.join;

import edu.test1.Test;

/**
 * Thread.sleep() 方法是不释放锁的，
 */
class ThreadB2 extends Thread {

    @Override
    public void run() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " beg "
                    + System.currentTimeMillis());
            System.out.println(Thread.currentThread().getName() + " end "
                    + System.currentTimeMillis());
        }
    }
}

class ThreadA2 extends Thread {

    private ThreadB2 threadB2;

    public ThreadA2(ThreadB2 threadB2) {
        this.threadB2 = threadB2;
    }

    @Override
    public void run() {
        synchronized (threadB2) {
            System.out.println(Thread.currentThread().getName() + " beg "
                    + System.currentTimeMillis());
            try {
                System.out.println("wait之前：" + threadB2.isAlive());
//                Thread.sleep(2000);
                threadB2.join();
                System.out.println("wait 之后" + threadB2.isAlive());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end "
                    + System.currentTimeMillis());
        }
    }
}

public class Run2 {

    public static void main(String[] args) {
        ThreadB2 threadB2 = new ThreadB2();
        threadB2.setName("BBB");
        ThreadA2 threadA2 = new ThreadA2(threadB2);
        threadA2.setName("AAA");

        threadA2.start();
        threadB2.start();
    }

}
