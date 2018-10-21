package edu.just.syn;

class MyObject {

    synchronized public void methodA() {
        try {
            System.out.println("begin methodA threadAName = "
                    + Thread.currentThread().getName() + " "
                    + System.currentTimeMillis());

            Thread.sleep(2000);

            System.out.println(Thread.currentThread().getName() + " end"
                    + " " + System.currentTimeMillis());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class ThreadB2 extends Thread {

    private MyObject object;

    public ThreadB2(MyObject object) {
        this.object = object;
    }

    @Override
    public void run() {
        object.methodA();
    }
}

public class ThreadA2 extends Thread {

    private MyObject object;

    public ThreadA2(MyObject object) {
        this.object = object;
    }

    @Override
    public void run() {
        object.methodA();
    }

    public static void main(String[] args) {
        MyObject object = new MyObject();
        ThreadA2 threadA2 = new ThreadA2(object);
        threadA2.setName("AA");
        threadA2.start();

        ThreadB2 threadB2 = new ThreadB2(object);
        threadB2.setName("BB");
        threadB2.start();

    }
}
