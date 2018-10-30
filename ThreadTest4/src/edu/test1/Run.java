package edu.test1;

class ThreadA extends Thread {

    private Object lock;

    public ThreadA(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " beg "
                    + System.currentTimeMillis());

            System.out.println(Thread.currentThread().getName() + " end "
                    + System.currentTimeMillis());
        }
    }

}

class ThreadB extends Thread {

    private Object lock;
    private ThreadA threadA;

    public ThreadB(Object lock, ThreadA threadA) {
        this.lock = lock;
        this.threadA = threadA;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " beg "
                    + System.currentTimeMillis());
            try {
                System.out.println("wait之前：" + threadA.isAlive());
                lock.wait();
                System.out.println("wait之后：" + threadA.isAlive());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " beg "
                    + System.currentTimeMillis());
        }
    }
}

public class Run {

    public static void main(String[] args) {
        Object lock = new Object();
        ThreadA threadA = new ThreadA(lock);
        threadA.setName("AAA");
        ThreadB threadB = new ThreadB(lock,threadA);
        threadB.setName("BBB");

        threadA.start();
        threadB.start();
    }

}
