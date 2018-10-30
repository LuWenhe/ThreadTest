package edu.join;

class ThreadB4 extends Thread {

    @Override
    public void run() {
        /**
         * 线程 BBB 持有对象锁 threadB4
         */
        this.methodB();
    }

    private void methodB() {
        System.out.println(Thread.currentThread().getName() + " begin b4 "
                + System.currentTimeMillis());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " end b4 "
                + System.currentTimeMillis());
    }
}

class ThreadA4 extends Thread {

    private ThreadB4 threadB4;

    public ThreadA4(ThreadB4 threadB4) {
        this.threadB4 = threadB4;
    }

    @Override
    public void run() {
        /**
         * 线程 AAA 持有对象锁 threadB4，
         */
        synchronized (threadB4) {
            try {
                System.out.println(Thread.currentThread().getName() + " begin a4 "
                        + System.currentTimeMillis());
                threadB4.start();
                /**
                 * 该方法是在线程 AAA 中调用的，此时使用 ThreadB4 的对象 threadB4 作为对象锁，
                 * 所以 join(0) 方法中的 isAlive() 方法是由对象锁 threadB4 执行的，
                 * wait() 方法的含义是让获得当前对象锁(threadB4)的线程(线程 AAA)等待，而由于
                 * 是在线程 AAA 中调用的，所以线程 AAA 获得了 join(0) 方法上的对象锁
                 */
                threadB4.join();
                System.out.println(Thread.currentThread().getName() + " end a4 "
                        + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

public class Run4 {

    /**
     * 线程 AAA 和线程 BBB 都是对同一对象锁 threadB4 进行操作的
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadB4 threadB4 = new ThreadB4();
        threadB4.setName("BBB");
        ThreadA4 threadA4 = new ThreadA4(threadB4);
        threadA4.setName("AAA");
        threadA4.start();
    }

}
