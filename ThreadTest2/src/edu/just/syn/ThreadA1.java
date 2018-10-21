package edu.just.syn;

class ThreadB1 extends Thread {

    private HashSelPrivateNum numRef;

    public ThreadB1(HashSelPrivateNum numRef) {
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

public class ThreadA1 extends Thread {

    private HashSelPrivateNum numRef;

    public ThreadA1(HashSelPrivateNum numRef) {
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
     * 多个线程访问多个对象，该实例创建了 2 个 HashSelPrivateNum 类的对象，就产生了 2 个对象锁
     *  当线程 Thread A1 执行 synchronized 方法 addI()，便持有该方法所属对象 numRef1 的锁
     *  此时线程 B 并不用等待，而是持有对象 numRef2 的锁。此时方法是异步执行的
     *
     */
    public static void main(String[] args) {
        HashSelPrivateNum numRef1 = new HashSelPrivateNum();
        HashSelPrivateNum numRef2 = new HashSelPrivateNum();

        ThreadA1 threadA1 = new ThreadA1(numRef1);
        threadA1.start();

        ThreadB1 threadB1 = new ThreadB1(numRef2);
        threadB1.start();
    }
}
