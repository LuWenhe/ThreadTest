package edu.just.vo;

class PrintString1 implements Runnable {

    private boolean isContinuePrint = true;

    public void setContinuePrint(boolean continuePrint) {
        isContinuePrint = continuePrint;
    }

    public void printStringMethod() {
        while (isContinuePrint) {

        }
    }

    @Override
    public void run() {
        printStringMethod();
    }
}

public class Run1 {

    public static void main(String[] args) throws InterruptedException {
        PrintString1 printString1 = new PrintString1();
        Thread thread = new Thread(printString1);
        thread.start();
        Thread.sleep(2000);
        System.out.println("我要停止它！" + Thread.currentThread().getName());
        printString1.setContinuePrint(false);
    }

}
