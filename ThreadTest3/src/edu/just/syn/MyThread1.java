package edu.just.syn;

class Task1 {

    private String getData1;
    private String getData2;

    public void doLongTimeTask() {
        try {
            System.out.println("begin task");
            Thread.sleep(2000);
            String priGetData1 = "长时间处理任务后从远程返回的值 1 " + Thread.currentThread().getName();
            String priGetData2 = "长时间处理任务后从远程返回的值 2 " + Thread.currentThread().getName();

            synchronized (this) {
                getData1 = priGetData1;
                getData2 = priGetData2;
            }

            System.out.println(getData1);
            System.out.println(getData2);
            System.out.println("end task\n");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread1_1_1 extends Thread {

    private Task1 task1;

    public MyThread1_1_1(Task1 task1) {
        this.task1 = task1;
    }

    @Override
    public void run() {
        task1.doLongTimeTask();
    }
}

public class MyThread1 extends Thread {

    private Task1 task1;

    public MyThread1(Task1 task1) {
        this.task1 = task1;
    }

    @Override
    public void run() {
        task1.doLongTimeTask();
    }

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        MyThread1 myThread1 = new MyThread1(task1);
        myThread1.setName("AA");
        myThread1.start();

        MyThread1_1_1 myThread1_1_1 = new MyThread1_1_1(task1);
        myThread1_1_1.setName("BB");
        myThread1_1_1.start();
    }
}
