package edu.just;

import java.util.ArrayList;
import java.util.List;

class MyList {

    private List list = new ArrayList();

    public void add() {
        list.add("高红颜");
    }

    public int size() {
        return list.size();
    }

}

class ThreadB extends Thread {

    private MyList myList;

    public ThreadB(MyList myList) {
        this.myList = myList;
    }

    @Override
    public void run() {
        System.out.println("sss");
        while (true) {
            if (myList.size() == 5) {
                System.out.println("== 5 了，线程 a 退出");
                return;
            }
        }
    }

}

public class ThreadA extends Thread {

    private MyList myList;

    public ThreadA(MyList myList) {
        this.myList = myList;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            myList.add();
            System.out.println(Thread.currentThread().getName()
                    + " 添加了 " + (i + 1) + " 个元素");
        }
    }

    public static void main(String[] args) {
        MyList myList = new MyList();
        ThreadA threadA = new ThreadA(myList);
        threadA.setName("AAA");
        threadA.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ThreadB threadB = new ThreadB(myList);
        threadB.setName("BBB");
        threadB.start();
    }
}
