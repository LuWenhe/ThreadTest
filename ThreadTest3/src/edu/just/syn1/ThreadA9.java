package edu.just.syn1;

class MyService {

    private String lock = "123";

    public void testMethod() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " begin "
                    + System.currentTimeMillis() + " lock = " + lock);
            lock = "456";
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end "
                    + System.currentTimeMillis() + " lock = " + lock);
        }
    }
}

class ThreadB9 extends Thread {

    private MyService myService;

    public ThreadB9(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethod();
    }
}

public class ThreadA9 extends Thread {

    private MyService myService;

    public ThreadA9(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethod();
    }

    /**
     * 如果有 Thread.sleep(50)
     * AAA begin 1540279787992 lock = 123
     * BBB begin 1540279800301 lock = 456
     * AAA end 1540279816108 lock = 456
     * BBB end 1540279822570 lock = 456
     *
     * 一开始方法 testMethod 是同步的，对象锁是 lock = "123"，当线程 AAA 先执行同步方法时，
     * 将对象锁 lock = "123" 修改成了 lock = "456"，这时，两个线程持有的就不是相同的对象锁了，
     * 此时线程 AAA 和 线程 BBB 异步执行
     */
    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        ThreadA9 threadA9 = new ThreadA9(service);
        threadA9.setName("AAA");

        ThreadB9 threadB9 = new ThreadB9(service);
        threadB9.setName("BBB");

        threadA9.start();
//        Thread.sleep(50);
        threadB9.start();
    }

}
