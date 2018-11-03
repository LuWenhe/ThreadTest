package edu.just.re;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Object 类中的 wait() 方法相当于 Condition 类中的 await() 方法
 * Object 类中的 wait(long timeout) 方法相当于 Condition 类中的 await(long time,TimeUnit unit) 方法
 * Object 类中的 notify() 方法相当于 Condition 类中的 signal() 方法
 * Object 类中的 notifyAll() 方法相当于 Contion 类中的 signalAll() 方法
 */
class MyService6 {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void await() {
        try {
            //当前线程获得锁
            lock.lock();
            System.out.println(Thread.currentThread().getName()
                    + " await begin的时间为：" + System.currentTimeMillis());
            //使当前线程进入等待状态，相当于 Object 中的 wait(long time) 方法
            condition.await();
            System.out.println(Thread.currentThread().getName()
                    + " await end的时间为：" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //当前线程释放锁
            lock.unlock();
        }
    }

    public void signal() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()
                    + " signal的时间为：" + System.currentTimeMillis());
            //唤醒进入等待状态的线程，相当于 Object 中的 notify() 方法
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

}

class ThreadA6 extends Thread {

    private MyService6 service6;

    public ThreadA6(MyService6 service6) {
        this.service6 = service6;
    }

    @Override
    public void run() {
        service6.await();
    }
}

public class Run6 {

    public static void main(String[] args) throws InterruptedException {
        MyService6 service6 = new MyService6();
        ThreadA6 threadA6 = new ThreadA6(service6);
        threadA6.start();
        Thread.sleep(2000);
        service6.signal();
    }

}
