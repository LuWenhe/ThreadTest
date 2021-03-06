package edu.just;

class Service5 {

    public void testMethod(Object lock) {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " be1gin wait "
                + System.currentTimeMillis());
            try {
                lock.wait();
                System.out.println(Thread.currentThread().getName() + " end wait "
                        + System.currentTimeMillis());
            } catch (InterruptedException e) {
                System.out.println("发生异常...");
                e.printStackTrace();
            }
        }
    }

    public void testMethod2(Object lock) {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " begin 2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end 2");
        }
    }

}

class ThreadB5 extends Thread {

    private Object lock;

    public ThreadB5(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        Service5 service5 = new Service5();
        service5.testMethod2(lock);
    }
}

public class ThreadA5 extends Thread {

    private Object lock;

    public ThreadA5(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        Service5 service5 = new Service5();
        service5.testMethod(lock);
    }

    /**
     * 在线程呈 wait() 状态时，调用线程对象的 interrupt() 方法会出现 InterruptedException 异常
     *
     * 1.执行完同步代码块就会释放对象的锁
     * 2.在执行同步代码块的过程中，遇到异常而导致线程终止，锁也会被释放
     * 3.在执行同步代码块的过程中，执行了锁所属对象的 wait 方法，这个过程会释放对象锁，
     *   而此线程对象会进入线程等待池中等待被唤醒
     */
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        ThreadA5 threadA5 = new ThreadA5(lock);
        threadA5.start();
        threadA5.interrupt();
        Thread.sleep(2000);

        ThreadB5 threadB5 = new ThreadB5(lock);
        threadB5.start();
    }
}
