package edu.just.syn;

public class PubVar {

    public String username = "AAA";
    public String password = "123456";

    synchronized public void setValue(String username, String password) {
        try {
            this.username = username;
            System.out.println(Thread.currentThread().getName() + " setValue begin"
                    + " user = " + this.username + " pas = " + this.password + " " + System.currentTimeMillis());
//            在给 password 赋值的时候将当前线程睡眠 2s
            Thread.sleep(2000);
            this.password = password;
            System.out.println(Thread.currentThread().getName() + " setValue end"
                    + " user = " + this.username + " pas = " + this.password + " " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getValue() {
        System.out.println(Thread.currentThread().getName() + " getvalue"
                + " user = " + username + " pas = " + password + " " + System.currentTimeMillis());
    }

}
