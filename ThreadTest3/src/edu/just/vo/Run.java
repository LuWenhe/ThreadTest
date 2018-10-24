package edu.just.vo;

class PrintString {

    private boolean isContinuePrint = true;

    public boolean isContinuePrint() {
        return isContinuePrint;
    }

    public void setCountPrint(boolean isContinuePrint) {
        this.isContinuePrint = isContinuePrint;
    }

    public void printStringMethod() {
        while (isContinuePrint == true) {
            System.out.println(Thread.currentThread().getName()
                    + " run printStringMethod " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Run {

    public static void main(String[] args) {
        PrintString printString = new PrintString();
        printString.printStringMethod();
        System.out.println("我要停止它！" + Thread.currentThread().getName());
        printString.setCountPrint(false);
    }

}
