package edu.just.syn1;

/**
 * synchronized 关键字加到 static 静态方法上是给 class 类上锁，
 * 而 synchronized 关键字加到非 static 静态方法上是给对象上锁
 */
class Service2 {

    synchronized public static void printA() {
        try {
            System.out.println(Thread.currentThread().getName() + " 在 "
                    + System.currentTimeMillis() + " 进入方法 printA");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " 在 "
                    + System.currentTimeMillis() + " 离开方法 printA");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public static void printB() {
        System.out.println(Thread.currentThread().getName() + " 在 "
                + System.currentTimeMillis() + " 进入方法 printB");
        System.out.println(Thread.currentThread().getName() + " 在 "
                + System.currentTimeMillis() + " 离开方法 printB");
    }

}

class ThreadB2 extends Thread {

    @Override
    public void run() {
        Service2.printB();
    }

}

public class ThreadA2 extends Thread {

    @Override
    public void run() {
        Service2.printA();
    }

    public static void main(String[] args) {
        ThreadA2 threadA2 = new ThreadA2();
        threadA2.setName("AAA");
        threadA2.start();

        ThreadB2 threadB2 = new ThreadB2();
        threadB2.setName("BBB");
        threadB2.start();
    }

}
