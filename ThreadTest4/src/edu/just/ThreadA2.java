package edu.just;

import java.util.ArrayList;
import java.util.List;

class MyList2 {

    private static List list = new ArrayList();

    public static void add() {
        list.add("anyString");
    }

    public static int size() {
        return list.size();
    }

}

class ThreadB2 extends Thread {

    private Object object;

    public ThreadB2(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        synchronized (object) {
            for (int i = 0; i < 10; i++) {
                MyList2.add();
                if (MyList2.size() == 5) {
                    object.notify();
                    System.out.println("已经消除通知");
                    System.out.println("aaa");
                }
                System.out.println(Thread.currentThread().getName()
                        + " 添加了" + (i + 1) + " 个元素");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class ThreadA2 extends Thread {

    private Object object;

    public ThreadA2(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        synchronized (object) {
            if (MyList2.size() != 5) {
                System.out.println(Thread.currentThread().getName()
                        + " wait begin " + System.currentTimeMillis());
                try {
                    object.wait();
                    System.out.println(Thread.currentThread().getName()
                            + " 被唤醒了");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()
                        + " wait end " + System.currentTimeMillis());
            }
        }
    }

    /**
     * 每个 Object 都实现了 wait() 和 notify() 方法，且必须用在被 synchronized 同步的 Object 临界区中
     * 线程 AAA 调用 wait 方法可以使调用该方法的线程 AAA 释放对象锁 object，然后从运行状态退出，进入等待队列，直到被再次唤醒
     * 之后线程 BBB 开始执行，等到了 i=5 的时候，用 notify 方法随机唤醒等待队列中等待同一共享资源的 一个 线程，
     * 使线程 AAA 退出等待队列，进入就绪状态，并视图重新获得对象锁，继续执行 wait 后面的代码
     *
     * 同时可以看到，线程 BBB 在执行完 notify 方法后，却不自动释放锁，还是等到将线程 BBB 调用的方法全部执行完，
     * 线程 AAA 才调用自己的方法，可见执行完 notify 之后，锁不会自动释放
     */
    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();
        ThreadA2 threadA2 = new ThreadA2(object);
        threadA2.setName("AAA");
        threadA2.start();
        Thread.sleep(2000);
        ThreadB2 threadB2 = new ThreadB2(object);
        threadB2.setName("BBB");
        threadB2.start();
    }
}
