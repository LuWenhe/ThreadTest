package edu.just.syn1;

/**
 * 此时因为两个线程调用的同步块不是一个对象锁，因此没有发生等待
 */
class Service8 {

    public void methodA(Object object) {
        synchronized (object) {
            System.out.println("methodA begin");
            boolean isFlag = true;
            while (isFlag) {

            }
            System.out.println("methodA end");
        }
    }

    public void methodB(Object object) {
        synchronized (object) {
            System.out.println("methodB begin");
            System.out.println("methodB end");
        }
    }

}

class ThreadB8 extends Thread {

    private Service8 service8;

    public ThreadB8(Service8 service8) {
        this.service8 = service8;
    }

    @Override
    public void run() {
        service8.methodB(new Object());
    }
}

public class ThreadA8 extends Thread {

    private Service8 service8;

    public ThreadA8(Service8 service8) {
        this.service8 = service8;
    }

    @Override
    public void run() {
        service8.methodA(new Object());
    }

    public static void main(String[] args) {
        Service8 service8 = new Service8();
        ThreadA8 threadA8 = new ThreadA8(service8);
        threadA8.start();

        ThreadB8 threadB8 = new ThreadB8(service8);
        threadB8.start();
    }
}
