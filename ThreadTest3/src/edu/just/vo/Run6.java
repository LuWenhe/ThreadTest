package edu.just.vo;

class Service2 {

    private boolean isCount = true;

    public void runMethod() {
        while (isCount) {
            /**
             * synchronized 可以保证可见性，即可以看到最新的修改后的变量的值
             */
            synchronized (this) {

            }
        }
        System.out.println("停下来了");
    }

    public void stopMethod() {
        isCount = false;
    }

}

class ThreadA1 extends Thread {

    private Service2 service2;

    public ThreadA1(Service2 service2) {
        this.service2 = service2;
    }

    @Override
    public void run() {
        service2.runMethod();
    }

}

class ThreadB1 extends Thread {

    private Service2 service2;

    public ThreadB1(Service2 service2) {
        this.service2 = service2;
    }

    @Override
    public void run() {
        service2.stopMethod();
    }
}

public class Run6 {

    public static void main(String[] args) throws InterruptedException {
        Service2 service2 = new Service2();
        ThreadA1 threadA1 = new ThreadA1(service2);
        threadA1.start();

        Thread.sleep(2000);

        ThreadB1 threadB1 = new ThreadB1(service2);
        threadB1.start();
        System.out.println("已经发起停止的命令了");
    }

}
