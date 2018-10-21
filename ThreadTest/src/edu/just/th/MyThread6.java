package edu.just.th;

public class MyThread6 extends Thread{

    private int count = 10;

    @Override
    public void run() {
        //此时不先执行 i--，而是直接在 print() 方法中执行
        System.out.println("由 " + Thread.currentThread().getName() +
                " 计算, count=" + (count--));
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread6 run = new MyThread6();
        Thread thread1 = new Thread(run);
        Thread thread2 = new Thread(run);
        Thread thread3 = new Thread(run);
        Thread thread4 = new Thread(run);
        Thread thread5 = new Thread(run);

        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(2000);
        thread4.start();
        thread5.start();

    }
}
