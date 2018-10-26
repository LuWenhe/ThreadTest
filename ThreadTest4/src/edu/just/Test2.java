package edu.just;

class Service2 {

    public void testMethod(Object lock) {

        synchronized (lock) {
            System.out.println(Thread.currentThread().getName()
                    + " beg1in wait " + System.currentTimeMillis());
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()
                    + " end wait " + System.currentTimeMillis());
        }

    }

}

class ThreadA6 extends Thread {

    private Object lock;

    public ThreadA6(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        Service2 service2 = new Service2();
        service2.testMethod(lock);
    }

}

class ThreadB6 extends Thread {

    private Object lock;

    public ThreadB6(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        Service2 service2 = new Service2();
        service2.testMethod(lock);
    }

}

class ThreadC6 extends Thread {

    private Object lock;

    public ThreadC6(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        Service2 service2 = new Service2();
        service2.testMethod(lock);
    }
}

/**
 * 如果只使用一次 notify() 方法，那么方法 notify() 仅仅随机唤醒一个线程
 */
class NotifyOne extends Thread {

    private Object lock;

    public NotifyOne(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("notifyOne 唤醒了一个线程 " + System.currentTimeMillis());
            lock.notify();
        }
    }
}

/**
 * 调用多个 notify() 方法会随机将 wait 状态的线程进行唤醒
 */
class NotifyMulti extends Thread {

    private Object lock;

    public NotifyMulti(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("NotifyMulti");
            lock.notify();
            lock.notify();
            lock.notify();
            lock.notify();
            lock.notify();
        }
    }
}

/**
 * 使用 notifyAll() 唤醒所有的线程
 */
class NotifyAll extends Thread {

    private Object object;

    public NotifyAll(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        synchronized (object) {
            System.out.println("notifyall");
            object.notifyAll();
        }
    }
}

public class Test2 {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        ThreadA6 threadA6 = new ThreadA6(lock);
        threadA6.start();
        ThreadB6 threadB6 = new ThreadB6(lock);
        threadB6.start();
        ThreadC6 threadC6 = new ThreadC6(lock);
        threadC6.start();

        Thread.sleep(2000);

        /*NotifyOne notifyOne = new NotifyOne(lock);
        notifyOne.start();*/
        NotifyMulti notifyMulti = new NotifyMulti(lock);
        notifyMulti.start();
        /*NotifyAll notifyAll = new NotifyAll(lock);
        notifyAll.start();*/
    }

}
