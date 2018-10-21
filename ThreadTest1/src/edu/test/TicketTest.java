package edu.test;

public class TicketTest extends Thread{

    private int ticket = 10;

    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + " 出售票 "
                    + ticket--);
            }
            else {
                return;
            }
        }
    }

    public static void main(String[] args) {
        TicketTest thread = new TicketTest();
        TicketTest thread2 = new TicketTest();
        TicketTest thread3 = new TicketTest();

        thread.start();
        thread2.start();
        thread3.start();
    }
}
