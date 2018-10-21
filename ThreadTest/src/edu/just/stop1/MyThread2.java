package edu.just.stop1;

public class MyThread2 extends Thread {

    private long i = 0;

    @Override
    public void run() {
        while (true) {
            i++;
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread2 thread2 = new MyThread2();
        /**
         * 此时线程 Thread-0 使用了 start 方法，代表该线程进入了就绪状态，并不会立刻执行，
         * 此时线程将进入线程队列排队，等待线程 main 让出 CPU 服务才可以执行
         */
        thread2.start();
        /**
         * 这里线程 main 睡眠 2s，并且让出了 CPU 服务，这个时候 Thread-0 才能执行，
         * 即执行 run 方法
         */
        Thread.sleep(2000);
        /**
         * 线程 main 暂停了 2s 之后，重新获得 CPU 并开始执行，然后将 Thread-0 挂起，即不在输出 i
         */
        thread2.suspend();
        System.out.println("main end");
    }

}
