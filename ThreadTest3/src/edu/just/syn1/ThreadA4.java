package edu.just.syn1;

class Service4 {

    public void printA() {
        synchronized (Service4.class) {
            try {
                System.out.println(Thread.currentThread().getName() + " 在 "
                        + System.currentTimeMillis() + " 进入 printA");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " 在 "
                        + System.currentTimeMillis() + " 离开 printA");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (Service4.class) {
            System.out.println(Thread.currentThread().getName() + " 在 "
                    + System.currentTimeMillis() + "进入 printB");
            System.out.println(Thread.currentThread().getName() + " 在 "
                    + System.currentTimeMillis() + "离开 printB");
        }
    }

}

class ThreadB4 extends Thread{

    private Service4 service4;

    public ThreadB4(Service4 service4) {
        this.service4 = service4;
    }

    @Override
    public void run() {
        service4.printA();
    }
}

public class ThreadA4 extends Thread {

    private Service4 service4;

    public ThreadA4(Service4 service4) {
        this.service4 = service4;
    }

    @Override
    public void run() {
        service4.printB();
    }

    public static void main(String[] args) {
        Service4 service4 = new Service4();
        Service4 service41 = new Service4();
        ThreadA4 threadA4 = new ThreadA4(service4);
        threadA4.setName("AAA");
        threadA4.start();

        ThreadB4 threadB4 = new ThreadB4(service41);
        threadB4.setName("BBB");
        threadB4.start();
    }

}
