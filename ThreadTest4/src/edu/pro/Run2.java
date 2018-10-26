package edu.pro;

import java.util.ArrayList;
import java.util.List;

class MyStack {

    private List list = new ArrayList();

    synchronized public void push() {
        try {
            while (list.size() == 1) {
                this.wait();
            }
            list.add(Thread.currentThread().getName()
                    + " anyString = " + Math.random());
            System.out.println(Thread.currentThread().getName()
                    + " push = " + list.size());
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public String pop() {
        String val = "";

        try {
            while (list.size() == 0) {
                System.out.println("pop操作中："
                        + Thread.currentThread().getName() + " 线程呈 wait 状态");
                this.wait();
            }
            val = "" + list.get(0);
            list.remove(0);
            this.notifyAll();
            System.out.println(Thread.currentThread().getName()
                     + " pop = " + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return val;
    }

}

class Produces {

    private MyStack myStack;

    public Produces(MyStack myStack) {
        this.myStack = myStack;
    }

    public void pushService() {
        myStack.push();
    }

}

class Customers {

    private MyStack myStack;

    public Customers(MyStack myStack) {
        this.myStack = myStack;
    }

    public void popService() {
        myStack.pop();
    }
}

class P_Thread extends Thread {

    private Produces produces;

    public P_Thread(Produces produces) {
        this.produces = produces;
    }

    @Override
    public void run() {
        while (true) {
            produces.pushService();
        }
    }
}

class C_Thread extends Thread {

    private Customers customers;

    public C_Thread(Customers customers) {
        this.customers = customers;
    }

    @Override
    public void run() {
        while (true) {
            customers.popService();
        }
    }
}

public class Run2 {

    public static void main(String[] args) {
        /*MyStack myStack = new MyStack();

        Produces produces = new Produces(myStack);
        Customers customers = new Customers(myStack);

        P_Thread p_thread = new P_Thread(produces);
        p_thread.setName("AAA");
        p_thread.start();

        C_Thread c_thread = new C_Thread(customers);
        c_thread.setName("BBB");
        c_thread.start();*/

        MyStack myStack = new MyStack();
        Produces produces = new Produces(myStack);
        Customers customers = new Customers(myStack);
        Customers customers2 = new Customers(myStack);
        Customers customers3 = new Customers(myStack);
        Customers customers4 = new Customers(myStack);
        Customers customers5 = new Customers(myStack);

        P_Thread p_thread = new P_Thread(produces);
        p_thread.start();

        C_Thread c_thread = new C_Thread(customers);
        C_Thread c_thread2 = new C_Thread(customers2);
        C_Thread c_thread3 = new C_Thread(customers3);
        C_Thread c_thread4 = new C_Thread(customers4);
        C_Thread c_thread5 = new C_Thread(customers5);

        c_thread.start();
        c_thread2.start();
//        c_thread3.start();
//        c_thread4.start();
//        c_thread5.start();
    }

}
