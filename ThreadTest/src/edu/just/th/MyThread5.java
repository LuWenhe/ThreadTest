package edu.just.th;

public class MyThread5 extends Thread{

    private int count = 5;

    @Override
    public void run() {
        count--;
        System.out.println("由 " + this.currentThread().getName() +
                "计算, count=" + count);
    }

    /*@Override
    public synchronized void run() {
        count--;
        System.out.println("由 " + this.currentThread().getName() +
                "计算, count=" + count);
    }*/

    public static void main(String[] args) {
        MyThread5 t1 = new MyThread5();
        Thread a = new Thread(t1, "AA");
        Thread b = new Thread(t1, "BB");
        Thread c = new Thread(t1, "CC");
        Thread d = new Thread(t1, "DD");
        Thread e = new Thread(t1, "EE");

        a.start();
        b.start();
        c.start();
        d.start();
        e.start();
    }

}
