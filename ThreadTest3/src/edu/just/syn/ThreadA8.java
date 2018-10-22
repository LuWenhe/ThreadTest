package edu.just.syn;

import java.util.ArrayList;
import java.util.List;

class MyOneList {

    private List list = new ArrayList();

    synchronized public void addMethod(String data) {
        list.add(data);
    }

    synchronized public int getSize() {
        return list.size();
    }

}

//创建业务类，用于存放数据
class MyService {

    /**
     * 如果不使用 synchronized 同步块，那么线程 AAA 和线程 BBB 在调用这个方法的时候，是以异步的方式
     * 添加元素的，可以同一时间两个线程都进入了这个方法里面，最终返回的集合中的元素个数为 2
     */
    /*public MyOneList addServiceMethod(MyOneList myOneList, String data) {
        try {
            //如果如果集合长度小于 1
            if (myOneList.getSize() < 1) {
                System.out.println(Thread.currentThread().getName() + " 进入了方法，时间是："
                        + System.currentTimeMillis() + " " + myOneList.getSize());
                //模拟从远程花费 2s 取回数据
                Thread.sleep(2000);
                //存入数据
                myOneList.addMethod(data);

                System.out.println(Thread.currentThread().getName() + " 存入了数据 "
                        + data + " 时间是：" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return myOneList;
    }*/

    /**
     * 我们对整个判断语句添加 synchronized 修饰，参数是 MyOneList 的对象 myOneList，即 synchronized(myOneList)
     * 因为该对象一开始是这样创建的： MyOneList oneList = new MyOneList()，并作为参数传入每个线程方法，
     * 之后每个线程调用 addServiceMethod(MyOneList myOneList, String data) 方法的时候，都是对同一个对象
     * onelist 进行操作，即只有这么一个实例对象。之后两个线程执行 addServiceMethod 方法就是同步执行的，即按照
     * 代码顺序执行了
     *
     * 同时，我也尝试者是以 synchronized(this) 来修饰代码块，发现结果还是异步执行的，说明每个线程调用的 this 是不同的，
     * 在每个线程中，addServiceMethod 方法是由对象 service 调用，而该对象是由 MyService myService = new MyService()
     * 语句产生，而每次的 myService 都是新 new 出来的，是不同的，而 myService 就是上面说的方法中的 this。如果使用 this
     * 作为 synchronized 代码块的参数，那么两个线程持有的两个不同的对象锁，但只是对自己而已，一起执行的话，还是异步执行的
     */
    public MyOneList addServiceMethod(MyOneList myOneList, String data) {
        try {
//            System.out.println(myOneList);
            System.out.println(this);

            synchronized (myOneList) {
                //如果如果集合长度小于 1
                if (myOneList.getSize() < 1) {
                    System.out.println(Thread.currentThread().getName() + " 进入了方法，时间是："
                            + System.currentTimeMillis());
                    //模拟从远程花费 2s 取回数据
                    Thread.sleep(2000);
                    //存入数据
                    myOneList.addMethod(data);

                    System.out.println(Thread.currentThread().getName() + " 存入了数据 "
                            + data + " 时间是：" + System.currentTimeMillis());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return myOneList;
    }

    /**
     * 直接在方法前面添加 synchronzied 关键字方法其实和上面使用 synchronized(this) 的原理是一样的，
     * 因为每个线程里面的 service 对象是不同的，因此相当于两个线程持有了 2 个对象锁，该代码依然是异步执行的
     */
    /*public synchronized MyOneList addServiceMethod(MyOneList myOneList, String data) {
        try {
            //如果如果集合长度小于 1
            if (myOneList.getSize() < 1) {
                System.out.println(Thread.currentThread().getName() + " 进入了方法，时间是："
                        + System.currentTimeMillis() + " " + myOneList.getSize());
                //模拟从远程花费 2s 取回数据
                Thread.sleep(2000);
                //存入数据
                myOneList.addMethod(data);

                System.out.println(Thread.currentThread().getName() + " 存入了数据 "
                        + data + " 时间是：" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return myOneList;
    }*/
}

class ThreadB8 extends Thread {

    private MyOneList myOneList;

    public ThreadB8(MyOneList myOneList) {
        this.myOneList = myOneList;
    }

    @Override
    public void run() {
        MyService myService = new MyService();
        myService.addServiceMethod(myOneList, "BBB");
    }
}

public class ThreadA8 extends Thread {

    private MyOneList myOneList;

    public ThreadA8(MyOneList myOneList) {
        this.myOneList = myOneList;
    }

    @Override
    public void run() {
        MyService myService = new MyService();
        myService.addServiceMethod(myOneList, "AAA");
    }

    public static void main(String[] args) throws InterruptedException {
        MyOneList oneList = new MyOneList();
        ThreadA8 threadA8 = new ThreadA8(oneList);
        threadA8.setName("AAA");
        threadA8.start();

        ThreadB8 threadB8 = new ThreadB8(oneList);
        threadB8.setName("BBB");
        threadB8.start();

        Thread.sleep(4000);
        System.out.println("listSize " + oneList.getSize());
    }

}
