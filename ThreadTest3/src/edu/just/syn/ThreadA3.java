package edu.just.syn;

/**
 * synchronized(this) 也是锁定当前对象的
 */
class Task3 {

    synchronized public void otherMethod() {
        System.out.println("otherMethod：" + this);
        System.out.println(" --------------------------run--otherMethod");
    }

    public void doLongTimeTask() {
        System.out.println("doLongTimeTask：" + this);
        synchronized (this) {
            for (int i = 0; i < 50; i++) {
                System.out.println("synchronized "
                        + Thread.currentThread().getName() + " i = " + (i + 1));
            }
        }
    }

}

class ThreadB3 extends Thread {

    private Task3 task3;

    public ThreadB3(Task3 task3) {
        this.task3 = task3;
    }

    @Override
    public void run() {
        //线程 BBB 调用 otherMethod
        task3.otherMethod();
    }
}

public class ThreadA3 extends Thread {

    private Task3 task3;

    public ThreadA3(Task3 task3) {
        this.task3 = task3;
    }

    @Override
    public void run() {
        //线程 AAA 调用 otherMethod
        task3.doLongTimeTask();
    }

    public static void main(String[] args) {
        Task3 task3 = new Task3();
        ThreadA3 threadA3 = new ThreadA3(task3);
        threadA3.setName("AAA");
        threadA3.start();

        ThreadB3 threadB3 = new ThreadB3(task3);
        threadB3.setName("BBB");
        threadB3.start();
    }
}
