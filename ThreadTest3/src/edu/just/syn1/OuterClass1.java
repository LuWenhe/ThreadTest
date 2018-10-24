package edu.just.syn1;

public class OuterClass1 {

    static class InnerClass1 {
        public void method1(InnerClass2 innerClass2) {
            System.out.println("innerclass1 method1 " + innerClass2);
            String threadName = Thread.currentThread().getName();

            synchronized (innerClass2) {
                System.out.println(threadName + " 进入 innerClass1 类中的 method1 方法");
                for (int i = 0; i < 10; i++) {
                    System.out.println(threadName + " i = " + i);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(threadName + " 离开 innerClass1 类中的 method1 方法");
            }
        }

        public synchronized void method2() {
            System.out.println("innerclass1 method2 " + this);
            String threadName = Thread.currentThread().getName();

            System.out.println(threadName + " 进入 innerClass1 类中的 method2 方法");
            for (int j = 0; j < 10; j++) {
                System.out.println(threadName + " j = " + j);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(threadName + " 离开 innerClass1 类中的 method2 方法");
        }
    }

    static class InnerClass2 {
        public synchronized void method1() {
            System.out.println("innerclass2 method1 " + this);
            String threadName = Thread.currentThread().getName();

            System.out.println(threadName + " 进入 innerClass2 类中的 method1 方法");
            for (int k = 0; k < 10; k++) {
                System.out.println(threadName + " k = " + k);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(threadName + " 离开 innerClass2 类中的 method1 方法");
        }
    }

    /**
     * 线程 T1 和 T3 持有同一个对象锁 innerClass2，线程 T2 持有对象锁 innerClass1，
     * 因为 T1 先执行，T1 先拿到锁，然后和 T2 交替执行，T3 只能等待 T1 执行完才能执行
     */
    public static void main(String[] args) {
        OuterClass1.InnerClass1 innerClass1 = new InnerClass1();
        OuterClass1.InnerClass2 innerClass2 = new InnerClass2();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //线程 T1 持有的对象锁是 innerClass2
                innerClass1.method1(innerClass2);
            }
        },"T1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //线程 T2 持有的锁是 innerClass1
                innerClass1.method2();
            }
        }, "T2");

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                //线程 T3 持有的锁是 innerClass2
                innerClass2.method1();
            }
        }, "T3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
