package edu.just.syn1;

/**
 * 因为 String 中有线程池的原因，导致两个线程拥有相同的锁，造成线程 B 不能执行
 */
class Service5 {

    public void print(String stringParam) {
        synchronized (stringParam) {
            try {
                while (true) {
                    System.out.println(Thread.currentThread().getName() +
                            " " + stringParam);
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class ThreadB5 extends Thread {

    private Service5 service5;

    public ThreadB5(Service5 service5) {
        this.service5 = service5;
    }

    @Override
    public void run() {
        service5.print("AAAA");
    }
}

public class ThreadA5 extends Thread {

    private Service5 service5;

    public ThreadA5(Service5 service5) {
        this.service5 = service5;
    }

    @Override
    public void run() {
        service5.print("AAAA");
    }

    public static void main(String[] args) {
        Service5 service5 = new Service5();
        ThreadA5 threadA5 = new ThreadA5(service5);
        threadA5.start();

        ThreadB5 threadB5 = new ThreadB5(service5);
        threadB5.start();
    }

}
