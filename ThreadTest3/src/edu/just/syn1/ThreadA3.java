package edu.just.syn1;

class Service3 {

    synchronized public static void printA() {
        try {
            System.out.println(Thread.currentThread().getName() + " 在 "
                    + System.currentTimeMillis() + " 进入 printA ");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " 在 "
                    + System.currentTimeMillis() + " 离开 printA ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public static void printB() {
        try {
            System.out.println(Thread.currentThread().getName() + " 在 "
                    + System.currentTimeMillis() + " 进入 printB");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " 在 "
                    + System.currentTimeMillis() + " 离开 printB");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void printC() {
        try {
            System.out.println(Thread.currentThread().getName() + " 在 "
                    + System.currentTimeMillis() + " 进入 printC");
            Thread.sleep(4000);
            System.out.println(Thread.currentThread().getName() + " 在 "
                    + System.currentTimeMillis() + " 离开 printC");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class ThreadB3 extends Thread{

    private Service3 service3;

    public ThreadB3(Service3 service3) {
        this.service3 = service3;
    }

    @Override
    public void run() {
        //调用 static 方法
        service3.printB();
    }
}

class ThreadC3 extends Thread {

    private Service3 service3;

    public ThreadC3(Service3 service3) {
        this.service3 = service3;
    }

    @Override
    public void run() {
        //调用非 static 方法
        service3.printC();
    }
}

public class ThreadA3 extends Thread {

    private Service3 service3;

    public ThreadA3(Service3 service3) {
        this.service3 = service3;
    }

    @Override
    public void run() {
        //调用 static 方法
        service3.printA();
    }

    /**
     * 线程 AAA 和 线程 BBB 调用 static 方法，线程 CCC 调用非 static 方法
     * 此时线程 AAA 和线程 BBB 持有的是类锁，而线程 CCC 是对象锁，类锁表示该类下的所有对象
     * 都是同步的，此时对象锁和类锁异步执行
     */
    /*public static void main(String[] args) {
        Service3 service3 = new Service3();
        ThreadA3 threadA3 = new ThreadA3(service3);
        threadA3.setName("AAA");
        threadA3.start();

        ThreadB3 threadB3 = new ThreadB3(service3);
        threadB3.setName("BBB");
        threadB3.start();

        ThreadC3 threadC3 = new ThreadC3(service3);
        threadC3.setName("CCC");
        threadC3.start();
    }*/

    /**
     * 即使下面有两个不同的实例对象 service3 和 service31，但是两个线程调用的是静态同步方法，
     * 这个时候，只要是该类的实例对象，不需要同一个对象，都可以同步执行这两个方法
     */
    public static void main(String[] args) {
        Service3 service3 = new Service3();
        Service3 service31 = new Service3();
        ThreadA3 threadA3 = new ThreadA3(service3);
        threadA3.setName("AAA");
        threadA3.start();

        ThreadB3 threadB3 = new ThreadB3(service31);
        threadB3.setName("BBB");
        threadB3.start();
    }
}
