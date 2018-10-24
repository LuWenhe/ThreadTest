package edu.just;

class Service5 {

    public void testMethod(Object lock) {
        synchronized (lock) {
            try {
                System.out.println(Thread.currentThread().getName() + " be1gin wait "
                    + System.currentTimeMillis());
                lock.wait();
                System.out.println(Thread.currentThread().getName() + " end wait "
                        + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("线程被打断了");
            }
        }
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
     * 1.执行完同步代码块就会释放对象的锁
     * 2.在执行同步代码块的过程中，遇到异常而导致线程终止，锁也会被释放
     * 3.在执行同步代码块的过程中，执行了锁所属对象的 wait 方法，这个过程会释放对象锁，
     *   而此线程对象会进入线程等待池中等待被唤醒
     */
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        ThreadA5 threadA5 = new ThreadA5(lock);
        threadA5.start();
        Thread.sleep(2000);
        threadA5.interrupt();
    }
}
