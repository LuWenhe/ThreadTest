package edu.just.stop1;

/**
 * suspend()：使用 suspend() 方法暂停线程
 * resume()：使用 resume() 方法恢复线程的执行
 */
public class MyThread extends Thread {

    private long i = 0;

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }

    @Override
    public void run() {
        while (true) {
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();

        // 使用 suspend() 暂停线程
        thread.suspend();
        System.out.println("A = " + System.currentTimeMillis() + " i = " +
                thread.getI());
        Thread.sleep(1000);
        System.out.println("A = " + System.currentTimeMillis() + " i = " +
                thread.getI());

        thread.resume();
        Thread.sleep(1000);

        System.out.println("A = " + System.currentTimeMillis() + " i = " +
                thread.getI());
        Thread.sleep(1000);
        System.out.println("A = " + System.currentTimeMillis() + " i = " +
                thread.getI());

    }
}
