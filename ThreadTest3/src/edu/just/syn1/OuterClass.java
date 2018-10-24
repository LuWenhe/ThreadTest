package edu.just.syn1;

public class OuterClass {

    /**
     * 此时内部类中的两个同步方法，使用的是不同的锁，因此交替执行
     * 如果把 method1() 中的同步块改为 synchronized(this)，则是同步执行了
     */
    static class InnerClass {

        public void method1() {
            synchronized ("其他的suo") {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " i = " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public synchronized void method2() {
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + " i = " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        InnerClass innerClass = new InnerClass();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                innerClass.method1();
            }
        }, "AAA");

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                innerClass.method2();
            }
        },"BBB");

        thread.start();
        thread1.start();
    }

}
