package edu.just.syn;

class ThreadB extends Thread {
    private HashSelPrivateNum numRef;

    public ThreadB(HashSelPrivateNum numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        try {
            numRef.addI("thread b");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ThreadA extends Thread {

    private HashSelPrivateNum numRef;

    public ThreadA(HashSelPrivateNum numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        try {
            numRef.addI("thread a");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 此时多个对象访问同一个对象
     *
     * 线程 Thread A 下先执行了带 synchronized 关键字的方法 addI()，此时线程 Thread A 就持有了对象 num 的锁
     * 此时线程 Thread B 只能等待线程 A 执行完了之后才能执行线程 B 中的 run() 方法，因此此时方法是同步的
     */
    public static void main(String[] args) throws InterruptedException {
        HashSelPrivateNum num = new HashSelPrivateNum();
        ThreadA threadA = new ThreadA(num);
        ThreadB threadB = new ThreadB(num);

        threadA.start();
        threadB.start();
    }
}
