package edu.just.pro;

/**
 * 非公平锁：一种获得锁的抢占机制，是随机获得锁的，先执行 lock() 的线程不一定
 *          先执行，导致一些线程一直拿不到锁
 */
public class NotFairTest {

    public static void main(String[] args) {
        FairService service = new FairService(false);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service.serviceMethod();
            }
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }

}
