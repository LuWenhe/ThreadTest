package edu.join;

class ThreadA3 extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " begin "
                + System.currentTimeMillis());
        /*try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println(Thread.currentThread().getName() + " end "
                + System.currentTimeMillis());
    }
}

class ThreadB3 extends Thread {

    private ThreadA3 threadA3;

    public ThreadB3(ThreadA3 threadA3) {
        this.threadA3 = threadA3;
    }

    /**
     * join(5000) 表示调用 join 方法的线程 AAA 所在的线程 BBB 最多阻塞 5s，即最多释放对象锁 threadA3 5s
     * 如果线程 BBB 在阻塞的过程中，线程 AAA 还没阻塞满 5s 就执行完成，那么线程 BBB 就
     * 获得对象锁 threadA3，然后执行 join 后面的代码，因此这个 5s 不是一定到等待的时间，而是最大时间
     */
    @Override
    public void run() {
        synchronized (threadA3) {
            System.out.println(Thread.currentThread().getName() + " beginb "
                    + System.currentTimeMillis());
            try {
                System.out.println(threadA3.isAlive());
                threadA3.start();
                /**
                 * while (threadA3.isAlive()) {
                 *      threadA3.wait(0);           //持有对象锁的那个线程 BBB 进入等待队列
                 * }
                 */
//                threadA3.join();

                /**
                 * while (this.isAlive()) {
                 *      this.wait(0);           //持有对象锁的线程 BBB 进入等待队列，没有其他线程来唤醒
                 * }
                 */
                join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " endb "
                    + System.currentTimeMillis());
        }
    }
}

public class Run3 {

    public static void main(String[] args) {
        ThreadA3 threadA3 = new ThreadA3();
        threadA3.setName("AAA");
        ThreadB3 threadB3 = new ThreadB3(threadA3);
        threadB3.setName("BBB");

        threadB3.start();
    }

}
