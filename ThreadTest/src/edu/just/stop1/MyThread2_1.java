package edu.just.stop1;

public class MyThread2_1 extends Thread {

    private String username = "1";
    private String password = "11";

    public void setValue(String u, String p) {
        this.username = u;

        String name = Thread.currentThread().getName();

        if (name.equals("线程a")) {
            System.out.println("停止 a 线程");
            //使调用该线程(Thread-0)的 线程(线程a)暂停，此时线程 a 被挂起，
            //不会执行后面的run后面的语句
            Thread.currentThread().suspend();
        }

        this.password = p;
    }

    public void print() {
        System.out.println(username + " " + password);
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread2_1 thread2_1 = new MyThread2_1();

        //创建线程 a
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                //在线程 a 中将线程 Thread-0 中的 username 改为 luwenhe，password 改为 123456
                thread2_1.setValue("luwenhe", "123456");
            }
        };

        thread1.setName("线程a");
        thread1.start();
        String name = Thread.currentThread().getName();
        System.out.println("name: " + name);
        Thread.sleep(2000);

        //创建线程 b
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                //在线程 b 中线程 Thread-0 中的 username 和 password 输出
                thread2_1.print();
            }
        };

        thread2.setName("线程b");
        thread2.start();

        thread2_1.print();
    }

}
