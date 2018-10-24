package edu.just.vo;

class Service {

    private boolean isContinueRun = true;

    public void runMethod() {
        while (isContinueRun) {}
        System.out.println("停下来了");
    }

    public void stopMethod() {
        System.out.println("开始停止");
        isContinueRun = false;
    }

}

class ThreadA extends Thread {

    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.runMethod();
    }

}

class ThreadB extends Thread {

    private Service service;

    public ThreadB(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.stopMethod();
    }
}

public class Run5 {

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        ThreadA threadA = new ThreadA(service);
        threadA.start();
        Thread.sleep(2000);

        ThreadB threadB = new ThreadB(service);
        threadB.start();
        System.out.println("已经发起停止的命令了");
    }

}
