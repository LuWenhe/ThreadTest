package edu.just.syn;

/**
 * 当有两个并发线程访问同一个对象 object 中的 synchronized(this) 同步代码块时，一段时间只能有一个线程被执行，
 * 另一个线程必须等待当前线程执行完这个代码块之后才能执行该同步代码块
 */
class ObjectService {

    public void serviceMethod() {
        // 使用 synchronized(this) 依然是同步代码块
        synchronized (this) {
            try {
                System.out.println(Thread.currentThread().getName() + " begin time = " + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " end time = " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class ThreadB extends Thread {

    private ObjectService service;

    public ThreadB(ObjectService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.serviceMethod();
    }

}

public class ThreadA extends Thread {

    private ObjectService service;

    public ThreadA(ObjectService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.serviceMethod();
    }

    public static void main(String[] args) {
        ObjectService service = new ObjectService();
        ThreadA a = new ThreadA(service);
        a.start();

        ThreadB b = new ThreadB(service);
        b.start();
    }

}
