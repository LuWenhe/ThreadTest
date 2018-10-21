package edu.test;

public class TicketTest2 implements Runnable {

    private int tickets = 10;

    @Override
    public void run() {
        while (true) {
            if (tickets > 0) {
                System.out.println(Thread.currentThread().getName() + " 出售票 " +
                        tickets--);
            }
        }
    }

    public static void main(String[] args) {
        TicketTest t = new TicketTest();
        //可以实现资源共享
        Thread thread = new Thread(t);
        Thread thread1 = new Thread(t);
        Thread thread2 = new Thread(t);

        thread.start();
        thread1.start();
        thread2.start();
    }
}
