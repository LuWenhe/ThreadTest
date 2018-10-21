package edu.just.syn;

public class ThreadA4 extends Thread {

    private PubVar pubVar;

    public ThreadA4(PubVar pubVar) {
        this.pubVar = pubVar;
    }

    @Override
    public void run() {
        pubVar.setValue("BBB","654321");
    }

    /**
     * Thread-0 setValue begin user = BBB pas = 123456 1540030412129
     * main getvalue user = BBB pas = 123456 1540030414129
     * Thread-0 setValue end user = BBB pas = 654321 1540030414129
     */
    public static void main(String[] args) throws InterruptedException {
        PubVar pubVar = new PubVar();
        ThreadA4 threadA4 = new ThreadA4(pubVar);
        threadA4.start();

        System.out.println("等待 4s ");
        Thread.sleep(2000);

        pubVar.getValue();
    }
}
