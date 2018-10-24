package edu.just;

class Service4 {

    //wait 方法
    public void testMethod(Object object) {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName()
                    + " begin wait() ");
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()
                    + " end wait() " + System.currentTimeMillis());
        }
    }

    //notify 方法
    public void synNotifyMethod(Object object) {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName()
                    + " begin notify() " + System.currentTimeMillis());
            object.notify();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()
                    + " end notify() " + System.currentTimeMillis());
        }
    }

}

class NotifyThread extends Thread {

    private Object lock;

    public NotifyThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        Service4 service4 = new Service4();
        service4.synNotifyMethod(lock);
    }

}

class SynNotifyMethodThread extends Thread {

    private Object lock;

    public SynNotifyMethodThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        Service4 service4 = new Service4();
        service4.synNotifyMethod(lock);
    }
}

public class ThreadA4 extends Thread {

    private Object lock;

    public ThreadA4(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        Service4 service4 = new Service4();
        service4.testMethod(lock);
    }

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        ThreadA4 threadA4 = new ThreadA4(lock);
        //先进入 wait
        threadA4.start();
        Thread.sleep(2000);

        NotifyThread notifyThread = new NotifyThread(lock);
        notifyThread.setName("AAA");
        //调用 notify
        notifyThread.start();

        SynNotifyMethodThread c = new SynNotifyMethodThread(lock);
        c.setName("BBB");
        //
        c.start();
    }

}
