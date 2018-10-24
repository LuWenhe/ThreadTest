package edu.just.syn1;

public class DealThread implements Runnable {

    public String username;
    public Object lock1 = new Object();
    public Object lock2 = new Object();

    public void setFlag(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        if (username.equals("a")) {
            synchronized (lock1) {
                try {
                    System.out.println("username = " + username);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock2) {
                    System.out.println("按 lock1 -> lock2 代码顺序执行");
                }
            }
        }

        if (username.equals("b")) {
            synchronized (lock2) {
                try {
                    System.out.println("username = " + username);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock1) {
                    System.out.println("按 lock2 -> lock1 代码顺序执行");
                }
            }
        }
    }

    /**
     * 线程 A 先持有对象 lock1 的锁，然后在持有 lock1 锁的时候又想去申请 lock2 的锁，此时他还没有释放 lock1 的锁；
     * 线程 B 先持有对象 lock2 的锁，然后在持有 lock2 锁的时候又想去申请 lock1 的锁，此时他还没有释放 lock2 的锁；
     * 两方都握有自己的锁不放弃，而同时申请另一方的锁，所以，此时就造成了死锁
     */
    public static void main(String[] args) throws InterruptedException {
        DealThread dealThread = new DealThread();
        dealThread.setFlag("a");

        Thread thread = new Thread(dealThread);
        thread.start();

        Thread.sleep(2000);

        dealThread.setFlag("b");
        Thread thread1 = new Thread(dealThread);
        thread1.start();
    }
}
