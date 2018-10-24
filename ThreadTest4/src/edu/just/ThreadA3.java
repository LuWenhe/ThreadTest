package edu.just;

class Service {

    public void testMethod(Object lock) {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " begin wait "
                    + System.currentTimeMillis());
            try {
                lock.wait();
//                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end wait "
                    + System.currentTimeMillis());
        }
    }

}

class ThreadB3 extends Thread {

    private Object lock;

    public ThreadB3(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        Service service = new Service();
        service.testMethod(lock);
    }
}

public class ThreadA3 extends Thread {

    private Object lock;

    public ThreadA3(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        Service service = new Service();
        service.testMethod(lock);
    }

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        ThreadA3 threadA3 = new ThreadA3(lock);
        threadA3.setName("AAA");
        threadA3.start();

        Thread.sleep(2000);

        ThreadB3 threadB3 = new ThreadB3(lock);
        threadB3.setName("BBB");
        threadB3.start();
    }
}
