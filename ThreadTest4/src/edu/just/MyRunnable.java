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
                    lock.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()
                        + " wait end time " + System.currentTimeMillis());
            }
        }
    };

    private static Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            synchronized (lock) {

                System.out.println(Thread.currentThread().getName()
                        + " wait begin time2 " + System.currentTimeMillis());
                System.out.println(Thread.currentThread().getName()
                        + " wait end time " + System.currentTimeMillis());
            }
        }
    };

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(runnable);
        thread.setName("AAA");
        thread.start();

//        Thread.sleep(1100);
        Thread.sleep(1);

        Thread thread2 = new Thread(runnable1);
        thread2.setName("BBB");
        thread2.start();
    }

}
