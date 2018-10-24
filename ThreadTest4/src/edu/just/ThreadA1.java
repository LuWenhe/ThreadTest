package edu.just;

public class ThreadA1 {

    public static void main(String[] args) throws InterruptedException {
        String str = new String("");
//        str.wait();
        str.notify();
    }

    /*public static void main(String[] args) throws InterruptedException {
        String str = new String();
        System.out.println("syn 上锁");
        synchronized (str) {
            System.out.println("syn 第一行");
            str.wait();
            System.out.println("wait 下的代码!");
        }
        System.out.println("syn 下的代码");
    }*/

}
