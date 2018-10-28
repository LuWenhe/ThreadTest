package edu.join;

class ThreadB4 extends Thread {

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " begin b"
                    + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " end b "
                    + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadA4 extends Thread {

    private ThreadB4 threadB4;

    public ThreadA4(ThreadB4 threadB4) {
        this.threadB4 = threadB4;
    }

    @Override
    public void run() {
        synchronized (threadB4) {
            try {
                System.out.println(Thread.currentThread().getName() + " begin a "
                        + System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + " end a "
                        + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Run4 {

    public static void main(String[] args) throws InterruptedException {
        ThreadB4 threadB4 = new ThreadB4();
        threadB4.setName("BBB");
        ThreadA4 threadA4 = new ThreadA4(threadB4);
        threadA4.setName("AAA");
        threadA4.start();
        Thread.sleep(2000);
        threadB4.start();
        //使当前线程 main 阻塞
        threadB4.join();
        System.out.println("main end " + System.currentTimeMillis());
    }

}
