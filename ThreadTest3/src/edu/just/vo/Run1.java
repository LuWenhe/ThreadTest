package edu.just.vo;

class PrintString1 implements Runnable {

    private boolean isContinuePrint = true;

    public void setContinuePrint(boolean continuePrint) {
        isContinuePrint = continuePrint;
    }

    public void printStringMethod() {
        while (isContinuePrint) {
            System.out.println(Thread.currentThread().getName()
                    + " run printStringMethod " + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        printStringMethod();
    }
}

public class Run1 {

    public static void main(String[] args) {
        PrintString1 printString1 = new PrintString1();
        Thread thread = new Thread(printString1);
        thread.start();
        System.out.println("我要停止它！" + Thread.currentThread().getName());
        printString1.setContinuePrint(false);
    }

}
