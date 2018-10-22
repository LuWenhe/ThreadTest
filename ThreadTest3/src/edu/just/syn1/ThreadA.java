package edu.just.syn1;

/**
 * 1.当多个线程同步执行 synchronized(x){} 同步代码块时呈现同步效果
 */
class Service {

    public void testMethod(Object object) {
        System.out.println(object);

        synchronized (object) {
            try {
                System.out.println(Thread.currentThread().getName() + " testMethod begin "
                        + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " testMethod end "
                        + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class ThreadB extends Thread {

    private Service service;
    private Object object;

    public ThreadB(Service service, Object object) {
        this.service = service;
        this.object = object;
    }

    @Override
    public void run() {
        service.testMethod(object);
    }
}

public class ThreadA extends Thread {

    private Service service;
    private Object object;

    public ThreadA(Service service, Object object) {
        this.service = service;
        this.object = object;
    }

    @Override
    public void run() {
        service.testMethod(object);
    }

    public static void main(String[] args) {
        Service service = new Service();
        Object object = new Object();
        Object object1 = new Object();
        ThreadA threadA = new ThreadA(service, object);
        threadA.setName("AAA");
        threadA.start();

        ThreadB threadB = new ThreadB(service, object1);
        threadA.setName("BBB");
        threadB.start();
    }

}
