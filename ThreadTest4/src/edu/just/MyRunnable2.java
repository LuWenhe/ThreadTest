package edu.just;

public class MyRunnable2 {

    static private Object lock = new Object();

    static private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName()
                        + " wait begin " + System.currentTimeMillis());
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()
                        + " wait end " + System.currentTimeMillis());
            }
        }
    };

    static private Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName()
                        + " notify begin " + System.currentTimeMillis());
//                lock.notify();
                System.out.println(Thread.currentThread().getName()
                        + " notify end " + System.currentTimeMillis());
            }
        }
    };

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(runnable);
        thread.setName("AAA");
        thread.start();
        Thread.sleep(200);
        Thread thread1 = new Thread(runnable2);
        thread1.setName("BBB");
        thread1.start();
    }

}
