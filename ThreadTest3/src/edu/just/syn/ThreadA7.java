package edu.just.syn;

import java.util.ArrayList;
import java.util.List;

/**
 * 多个线程调用同一个方法是随机的
 */
class MyList {

    private List list = new ArrayList();

    synchronized public void addMethod(String username) {
        System.out.println(Thread.currentThread().getName() + " 执行了add方法!!!");
        list.add(username);
        System.out.println(Thread.currentThread().getName() + " 退出了add方法!!!");
    }

    synchronized public int getSize() {
        System.out.println(Thread.currentThread().getName() + " 执行了getSize方法！！！");
        int sizeVal = list.size();
        System.out.println(Thread.currentThread().getName() + " 退出了getSize方法！！！"
                + "长度是: " + sizeVal);
        return sizeVal;
    }
}

class ThreadB7 extends Thread {

    private MyList list;

    public ThreadB7(MyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            list.addMethod("threadB7 " + (i + 1));
        }
    }
}

public class ThreadA7 extends Thread {

    private MyList list;

    public ThreadA7(MyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            list.addMethod("threadA7 " + (i + 1));
        }
    }

    public static void main(String[] args) {
        MyList list = new MyList();
        ThreadA7 threadA7 = new ThreadA7(list);
        threadA7.setName("AAA");
        threadA7.start();

        ThreadB7 threadB7 = new ThreadB7(list);
        threadB7.setName("BBB");
        threadB7.start();
    }

}
