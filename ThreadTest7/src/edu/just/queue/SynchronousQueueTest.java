package edu.just.queue;

import edu.just.time.Run;

import java.util.concurrent.SynchronousQueue;

/**
 * put()：往queue放进去一个element以后就一直wait直到有其他thread进来把这个element取走。
 * take()：取出并且remove掉queue里的element（认为是在queue里的。。。），取不到东西他会一直等
 *
 * offer()：往queue里放一个element后立即返回，如果碰巧这个element被另一个thread取走了，offer方法返回true，认为offer成功；否则返回false
 *
 */
class SynchronousService {

    private SynchronousQueue<String> ss = new SynchronousQueue<>();

    public void set() {
//        try {
            System.out.println(Thread.currentThread().getName()
                    + " 生产了一个产品");
        boolean s = ss.offer("陆文赫");
        System.out.println(Thread.currentThread().getName()
                    + " 结束生产 " + s);
        /*} catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public void get() {
//        try {
            System.out.println(Thread.currentThread().getName()
                    + " 消费了一个产品：" + ss.poll());
            System.out.println(Thread.currentThread().getName()
                    + " 结束消费");
        /*} catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

}

public class SynchronousQueueTest {

    public static void main(String[] args) {
        SynchronousService service = new SynchronousService();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service.set();
            }
        };

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                service.get();
            }
        };

        Thread thread = new Thread(runnable);
        Thread thread1 = new Thread(runnable1);
        thread.start();
        thread1.start();
    }

}
