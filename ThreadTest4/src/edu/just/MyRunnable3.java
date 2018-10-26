package edu.just;

public class MyRunnable3 {

    private static String lcok = new String();

    private static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            synchronized (lcok) {
                System.out.println("begin wait " + System.currentTimeMillis());
                try {
                    lcok.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end wait " + System.currentTimeMillis());
            }
        }
    };

    private static Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            synchronized (lcok) {
                System.out.println("begin notify " + System.currentTimeMillis());
                lcok.notify();
                System.out.println("end notify " + System.currentTimeMillis());
            }
        }
    };

    /*public static void main(String[] args) {
        Thread thread = new Thread(runnable);
        thread.setName("AAA");
        thread.start();

        Thread thread1 = new Thread(runnable2);
        thread1.setName("BBB");
        thread1.start();
    }*/

    /**
     * 如果通知过早，则会打乱程序正常的运行逻辑
     *
     * begin notify 1540474893673
     * end notify 1540474893673
     * begin wait 1540474893673
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(runnable);
        thread.setName("AAA");
        thread.start();

        Thread.sleep(11);

        Thread thread1 = new Thread(runnable2);
        thread1.setName("BBB");
        thread1.start();
    }
}
