package edu.just.syn;

class MyObject2 {

    synchronized public void methodA() {
        try {
            System.out.println("begin methodA threadName = "
                    + Thread.currentThread().getName() + " begin time = "
                    + System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " end Time = "
                    + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void methodB() {
        try {
            System.out.println("begin methodB threadName = "
                    + Thread.currentThread().getName() + " begin time = "
                    + System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " end Time = "
                    + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class ThreadB3 extends Thread {

    private MyObject2 object2;

    public ThreadB3(MyObject2 object2) {
        this.object2 = object2;
    }

    @Override
    public void run() {
        //调用非同步方法 methodB
        object2.methodB();
    }
}

public class ThreadA3 extends Thread {

    private MyObject2 object2;

    public ThreadA3(MyObject2 object2) {
        this.object2 = object2;
    }

    @Override
    public void run() {
        //调用同步方法 methodA
        object2.methodA();
    }


    /**
     * 1.如果线程 AA 调用同步类型的方法，线程 BB 调用非同步类型的方法
     *   此时线程 AA 先持有 object2 对象的锁，线程 BB 调用的是 object2 对象中的非同步的方法，因此这两个线程以异步的方式执行
     *
     * 2.如果线程 AA 调用同步类型的方法，线程 BB 也调用同步类型的方法
     *   此时线程 AA 持有 object2 对象的锁，线程 BB 调用的是另一个同步的方法，因为两个线程执行的是同一个对象，因此线程 BB 需要等待
     */
    public static void main(String[] args) {
        MyObject2 object2 = new MyObject2();
        ThreadA3 threadA3 = new ThreadA3(object2);
        threadA3.setName("AA");
        threadA3.start();

        ThreadB3 threadB3 = new ThreadB3(object2);
        threadB3.setName("BB");
        threadB3.start();
    }
}
