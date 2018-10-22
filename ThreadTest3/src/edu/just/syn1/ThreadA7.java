package edu.just.syn1;

/**
 * 使用同步方法，两个线程对同一个对象进行操作，线程 A 先拿到对象锁，因为里面是死循环，
 * 线程 B 只能不停的等待，发生锁死
 */
class Service7 {

    synchronized public void methodA() {
        System.out.println("methodA begin " + this);
        boolean isCountinueRun = true;
        while (isCountinueRun) {

        }
        System.out.println("methodA end");
    }

    synchronized public void methodB() {
        System.out.println("methodB begin " + this);
        System.out.println("methodB end");
    }

}

class ThreadB7 extends Thread {

    private Service7 service7;

    public ThreadB7(Service7 service7) {
        this.service7 = service7;
    }

    @Override
    public void run() {
        service7.methodB();
    }
}

public class ThreadA7 extends Thread {

    private Service7 service7;

    public ThreadA7(Service7 service7) {
        this.service7 = service7;
    }

    @Override
    public void run() {
        service7.methodA();
    }

    public static void main(String[] args) {
        Service7 service7 = new Service7();
        ThreadA7 threadA7 = new ThreadA7(service7);
        threadA7.start();

        ThreadB7 threadB7 = new ThreadB7(service7);
        threadB7.start();
    }

}
