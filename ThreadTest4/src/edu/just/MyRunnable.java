package edu.just;

public class MyRunnable {

    private static Object lock = new Object();

    private static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName()
                        + " wait begin time " + System.currentTimeMillis());
                try {
                    lock.wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()
                        + " wait end time " + System.currentTimeMillis());
            }
        }
    };

    public static void main(String[] args) {
        Thread thread = new Thread(runnable);
        thread.start();
    }


}
