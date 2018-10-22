package edu.just.syn1;

/**
 * synchronized(x){}
 * 2.当其他线程执行 x 对象中 synchronized 同步方法时呈同步效果，此时两个线程都是对同一个对象 x 执行操作，
 *   只不过一个是把 x 作为参数，类似 synchronized(object)；另一个是用 x 调用其他同步方法
 *
 * 3.当其他线程执行 x 对象方法里面的 synchronized(this) 代码块时也是同步的
 *   和上面一种一样，this 就是指调用这个方法的对象，即 object
 */
class MyObject {

    /*synchronized public void speedPrintString() {
        System.out.println(Thread.currentThread().getName()
                + " speedPrintString begin " + System.currentTimeMillis());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //        System.out.println("---------------------");
        System.out.println(Thread.currentThread().getName()
                + " speedPrintString end " + System.currentTimeMillis());
    }*/

    public void speedPrintString() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName()
                    + " speedPrintString begin " + System.currentTimeMillis());
            System.out.println("---------------------");
            System.out.println(Thread.currentThread().getName()
                    + " speedPrintString end " + System.currentTimeMillis());
        }
    }

}

class Service1 {

    public void testMethod(MyObject object) {

        synchronized (object) {
            try {
                System.out.println(Thread.currentThread().getName() + " testMethod begin "
                        + System.currentTimeMillis());
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + " testMethod end "
                        + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class ThreadB1 extends Thread {

    private MyObject object;

    public ThreadB1(MyObject object) {
        this.object = object;
    }

    @Override
    public void run() {
        //线程 B 是直接使用传入的 MyObject 对象调用同步方法
        object.speedPrintString();
    }

}

public class ThreadA1 extends Thread {

    private Service1 service1;
    private MyObject object;

    public ThreadA1(Service1 service1, MyObject object) {
        this.service1 = service1;
        this.object = object;
    }

    @Override
    public void run() {
        //线程 A 是把传入的 MyObject 对象作为参数，此时对象是 object
        service1.testMethod(object);
    }

    public static void main(String[] args) throws InterruptedException {
        Service1 service1 = new Service1();
        MyObject object = new MyObject();
        ThreadA1 threadA1 = new ThreadA1(service1, object);
        threadA1.setName("AAA");
        threadA1.start();

        Thread.sleep(2000);

        ThreadB1 threadB1 = new ThreadB1(object);
        threadB1.setName("BBB");
        threadB1.start();
    }

}