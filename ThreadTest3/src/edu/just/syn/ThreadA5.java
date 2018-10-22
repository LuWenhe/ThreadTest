package edu.just.syn;

class Test {

    private String obj = new String();

    public void synThis() {
        synchronized (this) {
            try {
                System.out.println(Thread.currentThread().getName()
                        + " method this begin " + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()
                        + " method this end " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void synNoThis() {
        //使用同步代码块锁非 this 对象，此时该代码块中的程序与同步方法时异步的
        synchronized (obj) {
            try {
                System.out.println(Thread.currentThread().getName()
                        + " method nothis begin " + System.currentTimeMillis());
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName()
                        + " method nothis end " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class ThreadB5 extends Thread {

    private Test test;

    public ThreadB5(Test test) {
        this.test = test;
    }

    @Override
    public void run() {
        test.synNoThis();
    }
}

public class ThreadA5 extends Thread{

    private Test test;

    public ThreadA5(Test test) {
        this.test = test;
    }

    @Override
    public void run() {
        test.synThis();
    }

    public static void main(String[] args) {
        Test test = new Test();
        ThreadA5 threadA5 = new ThreadA5(test);
        threadA5.setName("AAA");
        threadA5.start();

        ThreadB5 threadB5 = new ThreadB5(test);
        threadB5.setName("BBB");
        threadB5.start();
    }

}
