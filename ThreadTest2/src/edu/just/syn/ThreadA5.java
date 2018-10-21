package edu.just.syn;

class Service {

    synchronized public void service1() {
        System.out.println(Thread.currentThread().getName() + " service");
        service2();
    }

    synchronized public void service2() {
        System.out.println(Thread.currentThread().getName() + " service2");
        service3();
    }

    synchronized public void service3() {
        System.out.println(Thread.currentThread().getName() + " service3");
    }

}

public class ThreadA5 extends Thread {

    @Override
    public void run() {
        //线程 ThreadA5 得到了 service 对象锁
        Service service = new Service();
        //通过 service 对象调用对象中的其他同步方法
        service.service1();
    }

    public static void main(String[] args) {
        ThreadA5 threadA5 = new ThreadA5();
        threadA5.start();
    }

}

