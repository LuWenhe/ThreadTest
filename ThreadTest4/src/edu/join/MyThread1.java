package edu.join;

public class MyThread1 extends Thread {

    @Override
    public void run() {
        try {
            int time = 2000;
            System.out.println(Thread.currentThread().getName() + " 执行："
                    + time / 1000 + "s");
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * join 使调用这个方法的线程 Thread-0 正常执行 run() 方法，而使 Thread-0 所在的
     * 线程 main 进行无限期的阻塞，等待线程 Thread-0 销毁后(执行完 run 方法后)再继续执行
     * 线程 main 后面的代码
     *
     * 方法 join 具有使线程排队运行的作用。join 和 synchronized 和 sleep 的区别是：
     * 1.join 在底层使用 wait() 方法进行等待，会释放锁
     * 2.sleep 不会释放锁
     * 3.synchronized 使用对象监视器原理作为同步
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis());
        MyThread1 myThread1 = new MyThread1();
        myThread1.start();
        myThread1.join(1000);
        Thread.sleep(10000);
        System.out.println("我想当 myThread 对象执行完毕之后我在执行");
        System.out.println(System.currentTimeMillis());
    }
}
