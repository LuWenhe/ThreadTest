package edu.just.syn;

class Task {

    private String getData1;
    private String getData2;

    public synchronized void doLongTimeTask() {
        try {
            System.out.println("begin task");
            Thread.sleep(2000);
            getData1 = "长时间处理任务后从远程返回的值 1 " + Thread.currentThread().getName();
            getData2 = "长时间处理任务后从远程返回的值 1 " + Thread.currentThread().getName();

            System.out.println(getData1);
            System.out.println(getData2);
            System.out.println("end task\n");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread1_1 extends Thread {

    private Task task;

    public MyThread1_1(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        task.doLongTimeTask();
    }
}

public class MyThread extends Thread {

    private Task task;

    public MyThread(Task task) {
        this.task = task;
    }

    @Override
    public void run() {
        task.doLongTimeTask();
    }

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        MyThread thread = new MyThread(task);
        thread.start();

        MyThread1_1 thread1_1 = new MyThread1_1(task);
        thread1_1.start();

        /*Thread.sleep(2000);*/
    }
}
