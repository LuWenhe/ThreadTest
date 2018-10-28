package edu.join;

public class MyThread2 extends Thread {

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " begin time ="
                    + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " end time = "
                    + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * AAA begin time =1540622757391
     * Main end time =1540622759392
     * AAA end time = 1540622762392
     *
     * 线程 AAA 先执行，然后输出一行之后睡觉 5s，此时并不释放锁，只是让出 CPU 的资源给其他线程，
     * 在 5s 之内，线程 AAA 执行 join 方法，使线程 AAA 所在的线程 Main被阻塞，因为只是阻塞 2s，
     * 而线程 AAA 要睡眠 5s，因此，等不到线程 AAA 执行，就先执行线程 Main 中 join 后的代码，在等
     * 3s 后，在执行线程 AAA sleep 后的代码
     *
     * AAA begin time =1540623452338
     * AAA end time = 1540623457339
     * Main end time =1540623457339
     *
     * 如果执行 thread2.join(7000)，此时输出是上面一种，因为线程 Main 被阻塞 7s，此时轮到线程 AAA
     * 执行，因为只睡眠 5s，因此在线程 Main 阻塞的时候，线程 AAA 就已经执行完毕，等到线程 AAA 执行完，
     * 线程 Main 才开始执行 join 后面的代码
     */
    public static void main(String[] args) throws InterruptedException {
        MyThread2 thread2 = new MyThread2();
        thread2.setName("AAA");
        thread2.start();
        //调用 join 方法的线程 AAA 所在的线程 Main 释放对象锁 thread2，线程 AAA
        //获得对象锁之后开始执行 run 方法
        thread2.join(6000);

        System.out.println(Thread.currentThread().getName() + " end time ="
                + System.currentTimeMillis());
    }
}
