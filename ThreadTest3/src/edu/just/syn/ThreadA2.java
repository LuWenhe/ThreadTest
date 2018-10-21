package edu.just.syn;

/**
 * 在使用 synchronized(this) 代码块的时候，当一个线程访问 object 的一个 synchronized(this) 代码块的时候，
 * 其他线程对同一个 object 中所有其他 synchronized(this) 同步代码块的访问将被阻塞
 */
class ObjectService2 {

    public void serviceMethodA() {
        synchronized (this) {
            try {
                System.out.println(Thread.currentThread().getName()
                        + " methodA begin Time = " + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()
                        + " methodA end Time = " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void serviceMethodB() {
        synchronized (this) {
            try {
                System.out.println(Thread.currentThread().getName()
                        + " methodB begin time = " + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()
                        + " methodB end time = " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadB2 extends Thread {

    private ObjectService2 service2;

    public ThreadB2(ObjectService2 service2) {
        this.service2 = service2;
    }

    @Override
    public void run() {
        //线程 BBB 访问 ObjectService2 中的 synchronized(this) 同步代码块
        service2.serviceMethodB();
    }

}

public class ThreadA2 extends Thread {

   private ObjectService2 service2;

    public ThreadA2(ObjectService2 service2) {
        this.service2 = service2;
    }

    @Override
    public void run() {
        //线程 AAA 访问 ObjectService2 中的 synchronized(this) 同步代码块
        service2.serviceMethodA();
    }

    public static void main(String[] args) {
        ObjectService2 service2 = new ObjectService2();
        ThreadA2 threadA2 = new ThreadA2(service2);
        threadA2.setName("AAA");
        threadA2.start();

        ThreadB2 threadB2 = new ThreadB2(service2);
        threadB2.setName("BBB");
        threadB2.start();
    }
}
