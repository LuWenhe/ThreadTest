package edu.just.tl;

class Tools {

    public static ThreadLocal threadLocal = new ThreadLocal();

}

class ThreadA2 extends Thread {

    @Override
    public void run() {
        String name = Thread.currentThread().getName();

        try {
            for (int i = 0; i < 10; i++) {
                Tools.threadLocal.set(i + 1);
                System.out.println("线程" + name + " get的值是："
                        + Tools.threadLocal.get());
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadB2 extends Thread {

    @Override
    public void run() {
        String name = Thread.currentThread().getName();

        try {
            for (int i = 0; i < 10; i++) {
                Tools.threadLocal.set(i + 1);
                System.out.println("线程" + name + " get的值是：" + Tools.threadLocal.get());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Run2 {

    public static void main(String[] args) {
        ThreadA2 threadA2 = new ThreadA2();
        threadA2.setName("AAA");
        ThreadB2 threadB2 = new ThreadB2();
        threadB2.setName("BBB");
        threadA2.start();
        threadB2.start();

        try {
            for (int i = 0; i < 10; i++) {
                Tools.threadLocal.set(i + 1);
                System.out.println("线程" + Thread.currentThread().getName()
                        + " get的值是：" + Tools.threadLocal.get());
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
