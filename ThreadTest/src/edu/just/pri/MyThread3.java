package edu.just.pri;

public class MyThread3 extends Thread {

    private int i = 0;

    @Override
    public void run() {
        while (true) {
            i++;
            System.out.println("i = " + i);
            try {
                //使当前线程睡眠
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread3 myThread3 = new MyThread3();
        //使线程 Thread3 变成后台线程
        myThread3.setDaemon(true);
        myThread3.start();
//        Thread.sleep(2000);
        System.out.println("我离开 thread 对象不再打印了，也就是停止了");
    }
}
