package edu.just.pri;

/**
 * 优先级高的运行更快
 */
class ThreadB extends Thread {
    private int count = 0;

    public int getCount() {
        return count;
    }

    @Override
    public void run() {
        while (true) {
            count++;
        }
    }
}

public class ThreadA extends Thread {

    private int count = 0;

    public int getCount() {
        return count;
    }

    @Override
    public void run() {
        while (true) {
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadA threadA = new ThreadA();
        threadA.setPriority(Thread.NORM_PRIORITY - 3);
        threadA.start();

        ThreadB threadB = new ThreadB();
        threadB.setPriority(Thread.NORM_PRIORITY + 3);
        threadB.start();

        //使 main 线程睡眠 2s，即让出 CPU 2s 来执行线程A和B
        Thread.sleep(2000);
        threadA.stop();
        threadB.stop();

        System.out.println("线程 a 优先级是 2，运行速度为：" + threadA.getCount());
        System.out.println("线程 b 优先级是 8，运行速度为：" + threadB.getCount());
    }
}
