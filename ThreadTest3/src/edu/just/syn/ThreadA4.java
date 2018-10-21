package edu.just.syn;

class Service {

    private String username;
    private String password;

    /**
     * 如果 anyString 对象是全局的，每次使用 synchronized(anyString)同步代码块进行同步操作时，
     * 对象监视器就是同一个对象 anyString，此时两个线程同步执行，即按照顺序执行
     */
    private Object anyObject = new Object();

    public void setUsernameAndPassword(String username, String password) {
        /**
         * 如果 anyString 对象是在方法中的，局部的，那么每个线程调用这个方法都会 new 新的 anyString 对象，
         * 之后使用 synchronized(anyString) 同步代码块进行同步操作时，因为对象监视器不是同一个对象，此时运行的结果
         * 就是异步调用的，即两个线程交叉运行
         */
//        Object anyObject = new Object();

        //此时 anyString 对象作为对象监视器
        synchronized (anyObject) {
            try {
                System.out.println("线程名称为：" + Thread.currentThread().getName()
                        + " 在 " + System.currentTimeMillis() + " 进入同步代码块 "
                        + anyObject.hashCode());
                Thread.sleep(2000);
                this.password = password;
                this.username = username;
                System.out.println("线程名称为：" + Thread.currentThread().getName()
                        + " 在 " + System.currentTimeMillis() + " 退出同步代码块");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class ThreadB4 extends Thread {

    private Service service;

    public ThreadB4(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.setUsernameAndPassword("BBB","BBB123");
    }
}

public class ThreadA4 extends Thread {

    private Service service;

    public ThreadA4(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.setUsernameAndPassword("AAA", "AAA123");
    }

    public static void main(String[] args) {
        Service service = new Service();
        ThreadA4 threadA4 = new ThreadA4(service);
        threadA4.setName("线程 A");
        threadA4.start();

        ThreadB4 threadB4 = new ThreadB4(service);
        threadB4.setName("线程 B");
        threadB4.start();
    }
}
