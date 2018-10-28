package edu.join;

/**
 * Thread.sleep() 方法是不释放锁的，
 */
class Thread2 extends Thread {

    private Object object;

    public Thread2(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName() + " begin "
                    + System.currentTimeMillis());
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end "
                    + System.currentTimeMillis());
        }
    }
}

class Thread2_1 extends Thread {

    private Object object;

    public Thread2_1(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName() + " end "
                    + System.currentTimeMillis());

            System.out.println(Thread.currentThread().getName() + " end "
                    + System.currentTimeMillis());
        }
    }
}

public class Run2 {

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        Thread2 thread2 = new Thread2(object);
        thread2.start();
        Thread2_1 thread2_1 = new Thread2_1(object);
        thread2_1.start();
    }

}
