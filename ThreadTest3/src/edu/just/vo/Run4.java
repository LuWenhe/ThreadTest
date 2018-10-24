package edu.just.vo;

import java.util.concurrent.atomic.AtomicLong;

class MyService {

    public static AtomicLong atomicLong = new AtomicLong();

    //此时虽然 addAndGet 方法是原子的，但是方法和方法之间的调用却不是原子的
    /*public void addNum() {
        System.out.println(Thread.currentThread().getName()
                + " 加了 100 之后的值是：" + atomicLong.addAndGet(100));
        atomicLong.addAndGet(1);
    }*/

    //加了 synchronized 关键字之后，方法之间的调用也是原子的
    synchronized public void addNum() {
        System.out.println(Thread.currentThread().getName()
                + " 加了 100 之后的值是：" + atomicLong.addAndGet(100));
        atomicLong.addAndGet(1);
    }

}

class MyThread4 extends Thread {

    private MyService myService;

    public MyThread4(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.addNum();
    }
}

public class Run4 {

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        MyThread4[] myThread4s = new MyThread4[5];
        for (int i = 0; i < myThread4s.length; i++) {
            myThread4s[i] = new MyThread4(myService);
        }

        for (int i = 0; i < myThread4s.length; i++) {
            myThread4s[i].start();
        }

        Thread.sleep(2000);

        System.out.println(myService.atomicLong.get());
    }

}
