package edu.just;

class MyThread1 extends Thread {

    private Object lock;

    public MyThread1(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName()
                    + " 开始 wait time = " + System.currentTimeMillis());
            try {
                //wait 使线程 thread1 停止运行
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()
                    + " 结束 wait time = " + System.currentTimeMillis());
        }
    }
}

class MyThread2 extends Thread {

    private Object lock;

    public MyThread2(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName()
                    + " 开始 notify time = " + System.currentTimeMillis());
            //线程 thread2 使停止的 thread1 继续运行
            lock.notify();
            System.out.println(Thread.currentThread().getName()
                    + " 结束 notify time = " + System.currentTimeMillis());
        }
    }
}

class MyThread3 extends Thread {

    private Object lock;

    public MyThread3(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName()
                    + " 开始  wait time = " + System.currentTimeMillis());
            try {
                //wait 使线程 thread1 停止运行
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()
                    + " 结束 wait time = " + System.currentTimeMillis());
        }
    }
}

public class Test extends Thread {

    /**
     * wait() 是 Object 类的方法，该方法用来将线程停止执行，知道接到通知或者被中断为止。
     * 在调用 wait() 之前，线程必须获得该对象的对象级别锁，即只能在同步方法或同步块中调用 wait 方法。
     * 在执行 wait 方法后，当前线程释放锁，其他线程重新获得锁，如果调用 wait 时美亚持有锁，则会抛出异常
     *
     * notify() 也是 Object 类的方法，需要在同步方法或者同步块中调用，即调用前，线程必须获得对象级别的锁。
     * 如果有多个线程等待，则由线程规划器随便挑出其中一个呈 wait 状态的线程，对其发出通知 notify，
     * 并使他等待获取该对象的对象锁
     */
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        MyThread1 thread1 = new MyThread1(lock);
        thread1.setName("AAA");
        thread1.start();
        Thread.sleep(2000);

        /*MyThread3 thread3 = new MyThread3(lock);
        thread3.setName("CCC");
        thread3.start();
        Thread.sleep(2000);*/

        MyThread2 thread2 = new MyThread2(lock);
        thread2.setName("BBB");
        thread2.start();
    }

}
