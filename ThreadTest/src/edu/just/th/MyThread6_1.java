package edu.just.th;

public class MyThread6_1 implements Runnable {

    private int count = 5;

    //不能使用 for 语句，因为 for 语句里面只能被一个线程占用，即一直被一个线程执行减1，
    //只有当循环结束之后，其他线程才能得到运行的机会，并依次执行
    /*@Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            count--;
            System.out.println("由 " + Thread.currentThread().getName() +
                    " 计算, count=" + count);
        }
    }*/

    @Override
    public synchronized void run() {
        count--;
        System.out.println("由 " + Thread.currentThread().getName() +
                " 计算, count=" + count);
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread6_1 thread = new MyThread6_1();

        Thread t1 = new Thread(thread);
        Thread t2 = new Thread(thread);
        Thread t3 = new Thread(thread);
        Thread t4 = new Thread(thread);
        Thread t5 = new Thread(thread);

        t1.start();
        Thread.sleep(2000);
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
