package edu.just.tl;

class Tools6 {

    public static ThreadLocal<Integer> intLocal = new ThreadLocal<>();
    public static ThreadLocal<String> strLocal = new ThreadLocal<>();

}

class ThreadA6 extends Thread {

    public void set() {
        Tools6.intLocal.set(123);
        Tools6.strLocal.set("luwenhe");
        Tools6.strLocal.set("luwenhe2");
    }

    public void get() {
        Integer integer = Tools6.intLocal.get();
        String s = Tools6.strLocal.get();
        System.out.println("int: " + integer + " str: " + s);
    }

    @Override
    public void run() {
        System.out.println("------" + Thread.currentThread().getName() + "------");
        set();
        System.out.print("赋值后：");
        get();
    }
}

class ThreadB6 extends Thread {

    public void set() {
        Tools6.intLocal.set(456);
        Tools6.strLocal.set("luwenhe456");
    }

    public void get() {
        Integer integer = Tools6.intLocal.get();
        String s = Tools6.strLocal.get();
        System.out.println("int: " + integer + " str: " + s);
    }

    @Override
    public void run() {
        System.out.println("------" + Thread.currentThread().getName() + "------");
        set();
        System.out.print("赋值后：");
        get();
    }
}

public class Run6 {

    /**
     * ------AAA------
     * int: 123 str: luwenhe
     * ------BBB------
     * int: null str: null
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadA6 threadA6 = new ThreadA6();
        threadA6.setName("AAA");
        threadA6.start();

        Thread.sleep(2000);

        ThreadB6 threadB6 = new ThreadB6();
        threadB6.setName("BBB");
        threadB6.start();
    }

}
