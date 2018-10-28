package edu.join;

class ThreadA extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 开始执行");
        for (int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName() + " i = " + i);
        }
        System.out.println(Thread.currentThread().getName() + " 结束执行");
    }
}

class ThreadB extends Thread {

    @Override
    public void run() {
        try {
            ThreadA threadA = new ThreadA();
            threadA.setName("AAA");
            System.out.println(Thread.currentThread().getName() + " 开始执行");
            threadA.start();
            //线程 AAA 所在的线程 BBB 等待
            threadA.join();
            System.out.println(Thread.currentThread().getName() + " 结束执行");
        } catch (InterruptedException e) {
            System.out.println("线程 BBB 在 run end 处打印");
            e.printStackTrace();
        }
    }
}

class ThreadC extends Thread {

    private ThreadB threadB;

    public ThreadC(ThreadB threadB) {
        this.threadB = threadB;
    }

    @Override
    public void run() {
        super.run();
        System.out.println(Thread.currentThread().getName() + " 开始执行");
        threadB.interrupt();
        System.out.println(Thread.currentThread().getName() + " 结束执行");
    }
}

public class Run {

    public static void main(String[] args) {
        ThreadB threadB = new ThreadB();
        threadB.setName("BBB");
        threadB.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadC threadC = new ThreadC(threadB);
        threadC.setName("CCC");
        threadC.start();
    }

}
