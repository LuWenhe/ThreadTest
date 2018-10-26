package edu.just;

public class MyRunnable4 {

    private static String lock = new String();

    private static boolean isFirstRun = false;

    private static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            synchronized (lock) {
                //如果是 false
                while (!isFirstRun) {
                    System.out.println(Thread.currentThread().getName()
                            + " begin wait " + System.currentTimeMillis());
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
    };

    private static Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName()
                        + " begin notify " + System.currentTimeMillis());
                lock.notify();
                System.out.println(ThreadC6.currentThread().getName()
                        + " end notify " + System.currentTimeMillis());
                //将变量置为 true
                isFirstRun = true;
            }
        }
    };

    /**
     * AAA begin notify 1540475505472
     * AAA end notify 1540475505472
     */
    /*public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(runnable2);
        thread.setName("AAA");
        thread.start();
        ThreadC6.sleep(100);
        Thread thread1 = new Thread(runnable);
        thread1.setName("BBB");
        thread1.start();
    }*/

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(runnable);
        thread.setName("AAA");
        thread.start();
        ThreadC6.sleep(100);
        Thread thread1 = new Thread(runnable2);
        thread1.setName("BBB");
        thread1.start();
    }

}
