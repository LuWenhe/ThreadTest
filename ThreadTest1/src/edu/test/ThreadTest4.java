package edu.test;

public class ThreadTest4 extends Thread {

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + "is running");
        }
    }

    /**
     * 如果进程中只有后台进程在运行，那么这个进程就会结束
     */
    public static void main(String[] args) throws InterruptedException {
        //创建线程 Thread 4
        ThreadTest4 threadTest4 = new ThreadTest4();
        //设置 Thread 4 在后台运行，此时线程 Thread 4 就是后台进程
        threadTest4.setDaemon(true);
        //启动线程 Thread 4，因为线程 Thread 4 是后台线程，在主线程结束时就随之停止运行了
        threadTest4.start();
        //这里把CPU让给线程 Thread 4 一秒钟
        Thread.sleep(1000);
    }
}
