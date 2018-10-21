package edu.just.syn;

class Service1 {

    synchronized public void testMethod() {
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println(Thread.currentThread().getName() + " run beginTime = "
                    + System.currentTimeMillis());

            int i = 1;

            while (i == 1) {
                System.out.println(Thread.currentThread().getName()
                        + " run exceptionTime = " + System.currentTimeMillis());
//                Integer.parseInt("a");
            }
        } else {
            System.out.println("Thread B run time = " + System.currentTimeMillis());
        }
    }
}

class ThreadB7 extends Thread {

    private Service1 service1;

    public ThreadB7(Service1 service1) {
        this.service1 = service1;
    }

    @Override
    public void run() {
        service1.testMethod();
    }
}

public class ThreadA7 extends Thread {

    private Service1 service1;

    public ThreadA7(Service1 service1) {
        this.service1 = service1;
    }

    @Override
    public void run() {
        service1.testMethod();
    }

    public static void main(String[] args) throws InterruptedException {
        Service1 service1 = new Service1();
        ThreadB7 threadB7 = new ThreadB7(service1);
        threadB7.setName("a");
        threadB7.start();
        Thread.sleep(2000);

        ThreadA7 threadA7 = new ThreadA7(service1);
        threadA7.setName("b");
        threadA7.start();
    }
}
