package edu.just.syn1;

class User {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}

class Service10 {

    public void serviceMethodA(User user) {
        synchronized (user) {
            System.out.println(Thread.currentThread().getName()
                    + " start time = " + System.currentTimeMillis());
            user.setUsername("luwenhe");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()
                    + " end time = " + System.currentTimeMillis());
        }
    }

}

class ThreadB10 extends Thread {

    private Service10 service10;
    private User user;

    public ThreadB10(Service10 service10, User user) {
        this.service10 = service10;
        this.user = user;
    }

    @Override
    public void run() {
        service10.serviceMethodA(user);
    }
}

public class ThreadA10 extends Thread {

    private Service10 service10;
    private User user;

    public ThreadA10(Service10 service10, User user) {
        this.service10 = service10;
        this.user = user;
    }

    @Override
    public void run() {
        service10.serviceMethodA(user);
    }

    /**
     * 只要对象不变，即使对象的属性被改变，运行的结果依然是同步的
     */
    public static void main(String[] args) throws InterruptedException {
        Service10 service10 = new Service10();
        User user = new User("lukaijie", "123456");
        ThreadA10 threadA10 = new ThreadA10(service10, user);
        threadA10.setName("AAA");
        threadA10.start();

        Thread.sleep(2000);

        ThreadB10 threadB10 = new ThreadB10(service10, user);
        threadB10.setName("BBB");
        threadB10.start();
    }

}
