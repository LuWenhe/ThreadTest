package edu.pro;

/**
 * 一生产一消费
 * ValueObject 代表缓冲区，value = "" 表示缓冲区为空，value != "" 表示缓冲区为满
 *
 * 如果 value 不为 ""，说明缓存区是满的，那么生产者就 wait，不生产了，等待消费者消费完成之后再来通知生产者
 * 如果 value 为 ""，说明缓冲区不是满的，那么消费者就 wait，不消费了，等待生产者生产完成之后再来通知消费者
 */
class ValueObject {
    public static String value = "";
}

/**
 * 生产者
 */
class Producer {

    private String lock;

    public Producer(String lock) {
        this.lock = lock;
    }

    public void setValue() {
        synchronized (lock) {
            //如果 value 不为空，说明缓冲区是满的，生产者停止生产，需要消费者进行消费
            while (!ValueObject.value.equals("")) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //如果 value 为空，说明缓冲区是空的，生产者开始生产
            String val = System.currentTimeMillis() + " - " + System.nanoTime();
            System.out.println("set 的值是：" + val);
            ValueObject.value = val;
            lock.notify();
        }
    }
}

/**
 * 消费者
 */
class Customer {

    private String lock;

    public Customer(String lock) {
        this.lock = lock;
    }

    public void getValue() {
        synchronized (lock) {
            //如果 vlaue 为空，说明缓冲区为空，此时消费者停止消费
            while (ValueObject.value.equals("")) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //如果 values 不为空，说明缓冲区不为空，此时消费者开始消费
            System.out.println("get 的值是：" + ValueObject.value);
            ValueObject.value = "";
            lock.notify();
        }
    }

}

/**
 * 生产者线程
 */
class ThreadProducer extends Thread {

    private Producer producer;

    public ThreadProducer(Producer producer) {
        this.producer = producer;
    }

    @Override
    public void run() {
        while (true) {
            producer.setValue();
        }
    }
}

/**
 * 消费者线程
 */
class ThreadCustomer extends Thread {

    private Customer customer;

    public ThreadCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void run() {
        while (true) {
            customer.getValue();
        }
    }
}

/**
 * 一个生产一个消费
 */
public class Run {

    public static void main(String[] args) {
        String lock = new String("");
        Producer producer = new Producer(lock);
        Customer customer = new Customer(lock);

        ThreadProducer threadProducer = new ThreadProducer(producer);
        ThreadCustomer threadCustomer = new ThreadCustomer(customer);

        threadProducer.start();
        threadCustomer.start();
    }
}
