package edu.just.th;

public class MyThread4 extends Thread{

    private int count = 5;

    public MyThread4(String name) {
        this.setName(name);
    }

    @Override
    public void run() {
        while (count > 0) {
            count--;
            System.out.println("由 " + this.currentThread().getName() +
                    "计算, count=" + count);
        }
    }

    public static void main(String[] args) {
        MyThread4 a = new MyThread4("AA");
        MyThread4 b = new MyThread4("BB");
        MyThread4 c = new MyThread4("CC");

        a.start();
        b.start();
        c.start();
    }
}
