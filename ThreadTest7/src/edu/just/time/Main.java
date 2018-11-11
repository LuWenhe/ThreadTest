package edu.just.time;

public class Main extends Thread {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("线程被中断了");
                        return;
                    } else {
                        System.out.println("线程没有被中断");
                    }
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
        System.out.println("线程中断了，程序到这里了");
    }

}
