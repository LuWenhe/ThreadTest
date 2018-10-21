package edu.just.th;

/**
 * sleep()：在指定的毫秒数内让当前"正在执行的线程"休眠(暂停执行)
 * 这个"正在执行的线程"只得就是 this.currentThread() 返回的线程
 */
public class MyThread9 extends Thread {

   /* @Override
    public void run() {
        try {
            System.out.println("run threadName=" + this.currentThread().getName() +
                    " beg " + Thread.currentThread().isAlive());
            Thread.sleep(2000);
            System.out.println("run threadName=" + this.currentThread().getName() +
                    " end " + Thread.currentThread().isAlive());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void run() {
        try {
            System.out.println("run threadName=" + this.currentThread().getName() +
                    " beg=" + System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println("run threadName=" + this.currentThread().getName() +
                    " end=" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyThread9 myThread9 = new MyThread9();
        System.out.println("beg=" + System.currentTimeMillis());

//        myThread9.run();
        myThread9.start();
        System.out.println("end=" + System.currentTimeMillis());
    }
}