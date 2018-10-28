package edu.join;

public class MyThread extends Thread {

    @Override
    public void run() {
        try {
            int secondVal = (int) (Math.random() * 1000);
            System.out.println(secondVal);
            Thread.sleep(secondVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("我想当 myThread 对象执行完毕之后我在执行");
        System.out.println("但上面代码中的 sleep 中的值应该写多少呢？");
        System.out.println("答案是：不能确定");
    }

}
