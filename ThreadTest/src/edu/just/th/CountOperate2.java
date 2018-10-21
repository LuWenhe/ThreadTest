package edu.just.th;

/**
 * isAlive()：判断当前线程是否处于活动状态
 */
public class CountOperate2 extends Thread {

    public CountOperate2() {
        System.out.println("CountOperate--begin");

        System.out.println("Thread.currentThread().getname(): " +
                Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive(): " +
                Thread.currentThread().isAlive());
        //此时的 this 代表当前的实例对象，即 Thread-0，由于还没有执行 start 操作，
        //因此不是处于活动状态的
        System.out.println("this.getName(): " + this.getName());
        System.out.println("this.isAlive(): " + this.isAlive());
        System.out.println("CountOperate--end");
    }

    @Override
    public void run() {
        System.out.println("run--beign");
        System.out.println("Thread.currentThread().getName(): " +
                Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive(): " +
                Thread.currentThread().isAlive());
        System.out.println("this.getName(): " + this.getName());
        System.out.println("this.isAlive(): " + this.isAlive());
        System.out.println("run--end");
    }

    public static void main(String[] args) {
        CountOperate2 c = new CountOperate2();
        System.out.println();
        c.start();
    }
}
