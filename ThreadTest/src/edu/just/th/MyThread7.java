package edu.just.th;

/**
 * 测试 currentThread()：返回代码段正在被哪个线程调用的信息
 */
public class MyThread7 extends Thread{

    static {
        System.out.println("静态代码块：" + Thread.currentThread().getName());
    }

    public MyThread7() {
        System.out.println("构造方法的打印: " + Thread.currentThread().getName());
        System.out.println("this.name(): " + this.getName());
    }

    @Override
    public void run() {
        System.out.println("run方法的打印: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        MyThread7 thread = new MyThread7();
        /**
         * 静态代码块：main
         * 构造方法的打印: main
         * this.name(): Thread-0
         * run方法的打印: Thread-0
         *
         * 说明 MyThread7 类的构造函数时被 main 线程调用的，因此输出 main
         * this.name 中的 this 代表线程实例本身，就是 Thread-0
         * run 方法是被名称为 Thread-0 的线程调用的，因此输出 Thread-0
         */
//        thread.start();

        /**
         * 静态代码块：main
         * 构造方法的打印: main
         * this.name(): Thread-0
         * run方法的打印: main
         *
         * 说明 MyThread 7 类的构造函数被 main 线程调用，
         * 由于没有执行 start 方法，run 方法直接被调用了，导致代码还是同步执行的
         */
        thread.run();
    }

}
