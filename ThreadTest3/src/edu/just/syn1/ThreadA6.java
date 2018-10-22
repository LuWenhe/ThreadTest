package edu.just.syn1;

/**
 * 将方法中的参数改成了 Object，这样就避免了因为 String 线程池导致的每个线程持有同一个锁，
 * 此时每个 object 对象是不同的，即每个线程持有不同的类锁
 */
class Service6 {

    public static void print(Object object) {
        System.out.println(Thread.currentThread().getName() + " " + object);

        synchronized (object) {
            while (true) {
                try {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class ThreadB6 extends Thread {

    private Service6 service6;

    public ThreadB6(Service6 service6) {
        this.service6 = service6;
    }

    @Override
    public void run() {
        Object object = new Object();
        service6.print(object);
    }
}

public class ThreadA6 extends Thread {

    private Service6 service6;

    public ThreadA6(Service6 service6) {
        this.service6 = service6;
    }

    @Override
    public void run() {
        Object object = new Object();
        service6.print(object);
    }

    public static void main(String[] args) {
        Service6 service6 = new Service6();
        Service6 service66 = new Service6();
        ThreadA6 threadA6 = new ThreadA6(service6);
        threadA6.start();

        ThreadB6 threadB6 = new ThreadB6(service66);
        threadB6.start();
    }

}
