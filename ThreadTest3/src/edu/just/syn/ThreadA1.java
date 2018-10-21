package edu.just.syn;

/**
 * 不在 synchronized 代码块中就是异步执行，在 synchronized 块中就是同步执行
 */
class TaskA {

    public void doLongTimeTask() {
        //没在 synchronized(this) 代码块中的两个线程交替输出
        for (int i = 0; i < 50; i++) {
            System.out.println("no synchronized " + Thread.currentThread().getName()
                    + " i = " + (i + 1));
        }

        System.out.println("");

        //在 synchronized(this) 代码块中的线程排队执行
        synchronized (this) {
            for (int i = 0; i < 50; i++) {
                System.out.println("synchronized " + Thread.currentThread().getName()
                        + " i = " + (i + 1));
            }
        }
    }

}

class ThreadB1 extends Thread {

    private TaskA taskA;

    public ThreadB1(TaskA taskA) {
        this.taskA = taskA;
    }

    @Override
    public void run() {
        taskA.doLongTimeTask();
    }

}

public class ThreadA1 extends Thread {

    private TaskA taskA;

    public ThreadA1(TaskA taskA) {
        this.taskA = taskA;
    }

    @Override
    public void run() {
        taskA.doLongTimeTask();
    }

    public static void main(String[] args) {
        TaskA task = new TaskA();
        ThreadA1 threadA1 = new ThreadA1(task);
        threadA1.setName("AAA");
        threadA1.start();

        ThreadB1 threadB1 = new ThreadB1(task);
        threadB1.setName("BBB");
        threadB1.start();
    }
}
