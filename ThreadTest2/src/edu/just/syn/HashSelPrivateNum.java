package edu.just.syn;

/**
 * 关键字 synchroized 取得的锁是对象锁，而不是把一段代码或者方法(函数)作为锁
 *
 * 1.
 * 如果多个线程访问同一个对象，那么哪个线程先执行带 synchronized 关键字的方法，哪个线程就持有该方法所属对象的锁，
 * 同时其他线程只能呈等待状态，此时方法的同步执行的
 *
 * 2.
 * 如果多个线程访问多个对象，则 JVM 会创建多个锁，此时每个线程持有的锁是不同的，不会因为一个线程持有一个锁，而另一个
 * 线程需要等待的情况，此时方法是异步执行的
 */
class HashSelPrivateNum {

    //如果变量不是方法的私有变量，此时变成了公共变量，则有可能出现线程安全问题，此时需要加 synchronized 关键字
    private int num = 0;

    public synchronized void addI(String username) throws InterruptedException {
        //该变量是 addI 方法内部的私有变量，此时不加 synchronized 关键字也不会存在线程安全问题
//        int num;

        if (username.equals("thread a")) {
            num = 100;
            System.out.println("a set over" + " " + System.currentTimeMillis());
            //使当前线程休眠
            Thread.sleep(2000);
        } else {
            num = 200;
            System.out.println("b set over" + " " + System.currentTimeMillis());
        }
        System.out.println(username + " num = " + num + " " + System.currentTimeMillis());
    }
}