package edu.just.vo;

public class VolatileTest {

    public static volatile int race = 0;

    public static void increase() {
        race++;
    }

    private static final int THREADS_COUNT = 20;

    /**
     * volatile 虽然能保证每次读取的是最小的 race，但是
     * volatile 没办法保证对变量的操作的原子性
     *
     * 自增操作包括 1.读取变量的原始值，2.进行加1操作，3.写入工作内存三个步骤
     */
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        /*System.out.println(Thread.currentThread().getName() + " "
                                + "正在执行...");*/
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        Thread.sleep(2000);

        System.out.println(race);
    }

}
