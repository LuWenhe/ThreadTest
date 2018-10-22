package edu.just.syn;

/**
 * 使用 synchronized(anyObject)同步代码块 与 synchronized 方法是异步调用的，
 * 因为此时的监视器不同，前者的监视器是先创建的对象 anyObject，后者则是 this
 */
class Service1 {

//    private Object anyObject = new Object();

    public void a() {
        Object anyObject = new Object();

        synchronized (anyObject) {
            try {
                System.out.println(Thread.currentThread().getName()
                        + " a begin " + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()
                        + " a end " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void b() {
        System.out.println(Thread.currentThread().getName()
                + " b begin " + System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName()
                + " b end " + System.currentTimeMillis());
    }

}

class ThreadB6 extends Thread {

    private Service1 service1;

    public ThreadB6(Service1 service1) {
        this.service1 = service1;
    }

    @Override
    public void run() {
        service1.b();
    }

}

public class ThreadA6 extends Thread {

    private Service1 service1;

    public ThreadA6(Service1 service1) {
        this.service1 = service1;
    }

    @Override
    public void run() {
        service1.a();
    }

    public static void main(String[] args) {
        Service1 service1 = new Service1();
        ThreadA6 threadA6 = new ThreadA6(service1);
        threadA6.setName("AAA");
        threadA6.start();

        ThreadB6 threadB6 = new ThreadB6(service1);
        threadB6.setName("BBB");
        threadB6.start();
    }
}
