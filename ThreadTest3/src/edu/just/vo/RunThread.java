package edu.just.vo;

public class RunThread extends Thread {

    private boolean isRunning = true;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void run() {
        System.out.println("进入 run 了");
        while (isRunning == true) {
            synchronized (this) {}
            /**
             * 这里如果使用 synchronized(this)，那么不加 volatile 也是会跳出循环的，
             * 因为 synchronized 可以使多个线程之间具有可见性，synchronized 能够保证同一
             * 时刻只有一个线程获取锁然后执行同步代码，并在释放锁之前会将变量的修改刷新到
             * 主存中
             */
//            System.out.println("线程被停止了！");
        }
    }

    /**
     * 线程 runthread 执行 run 方法的时候，由于用到了主内存中的变量 isRunning，
     * 此时需要将这个变量拷贝一份，存在自己的工作内存中；
     * 而 setRunning(false) 是在线程 main 中执行的。在 main 中设置的 isRunning(false) 是在
     * 主内存中的，此时只是更新了主内存的 isRunning 参数，而线程 runthread 中的 isRunning 因为
     * 只是拷贝，并没有及时更新，还是 true，因此就变成了死循环了
     *
     * 当我们在 isRunning 变量上加上 volatile 关键字，当我们在线程 main 中将 runThread 置为 false 时，
     * 实际经历了两个过程，1.修改线程 main 工作内存中的值，2.将修改的值写入主内存；此时会使线程 runthread
     * 的工作内存中的缓存变量 inRunning 失效，然后线程 runthread读取时，发现自己的缓冲行已经失效了，
     * 此时它会等待缓冲行对应的主存地址被更新之后，然后线程 runthread 强制的从主存中读取
     */
    public static void main(String[] args) throws InterruptedException {
        RunThread runThread = new RunThread();
        runThread.start();
        Thread.sleep(1);
        runThread.setRunning(false);
        System.out.println("已经赋值为 false 了");
    }

}
