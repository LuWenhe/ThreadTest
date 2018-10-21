package edu.just.th;

/**
 * 测试 currentThread()：返回代码段正在被哪个线程调用
 */
public class CountOperate extends Thread {

    public CountOperate() {
        System.out.println("CountOperate--begin");

        System.out.println("Thread.currentThread().getname(): " +
                Thread.currentThread().getName());
        System.out.println("this.getName(): " + this.getName());
        System.out.println("CountOperate--end");
    }

    @Override
    public void run() {
        System.out.println("run--beign");
        System.out.println("Thread.currentThread().getName(): " +
                Thread.currentThread().getName());
        System.out.println("this.getName(): " + this.getName());
        System.out.println("run--end");
    }

    public static void main(String[] args) {
        CountOperate c = new CountOperate();
        System.out.println();
        c.start();
    }
}
