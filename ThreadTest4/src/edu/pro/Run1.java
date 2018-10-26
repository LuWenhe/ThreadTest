package edu.pro;

/**
 * 多生产和多消费
 */
class ObjectVal {
    public static String val = "";
}

class Producer1 {

    private String lock;

    public Producer1(String lock) {
        this.lock = lock;
    }

    public void setValue() {
        synchronized (lock) {
            if (!ObjectVal.val.equals("")) {
                System.out.println("生产者 "
                        + Thread.currentThread().getName() + " waiting了");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("生产者 " + Thread.currentThread().getName()
                    + " run了");
            String time = System.currentTimeMillis() + " - " + System.nanoTime();
            ObjectVal.val = time;
            lock.notify();
        }
    }

}

class Customer1 {

    private String lock;

    public Customer1(String lock) {
        this.lock = lock;
    }

    public void getValue() {
        synchronized (lock) {
            if (ObjectVal.val.equals("")) {
                System.out.println("消费者 " + Thread.currentThread().getName()
                        + " waiting了");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("消费者：" + Thread.currentThread().getName()
                    + " run了");
            ObjectVal.val = "";
            lock.notify();
        }
    }

}

class ThreadProduce1 extends Thread {

    private Producer1 producer1;

    public ThreadProduce1(Producer1 producer1) {
        this.producer1 = producer1;
    }

    @Override
    public void run() {
        while (true) {
            producer1.setValue();
        }
    }

}

class ThreadCustomer1 extends Thread {

    private Customer1 customer1;

    public ThreadCustomer1(Customer1 customer1) {
        this.customer1 = customer1;
    }

    @Override
    public void run() {
        while (true) {
            customer1.getValue();
        }
    }
}

public class Run1 {

    public static void main(String[] args) throws InterruptedException {
        String lock = new String("55");
        Producer1 producer1 = new Producer1(lock);
        Customer1 customer1 = new Customer1(lock);

        ThreadProduce1 tProduce1 = new ThreadProduce1(producer1);
        tProduce1.setName("Produce");
        tProduce1.start();

        ThreadProduce1 tProduce2 = new ThreadProduce1(producer1);
        tProduce2.setName("Produce2");
        tProduce2.start();

        ThreadCustomer1 tCustomer1 = new ThreadCustomer1(customer1);
        tCustomer1.setName("Customer");
        tCustomer1.start();
    }

}
