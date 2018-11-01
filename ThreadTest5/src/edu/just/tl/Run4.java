package edu.just.tl;

/**
 * 在 ThreadLocal 类中存入默认值
 */
class ThreadLocalTest extends ThreadLocal {

    @Override
    protected Object initialValue() {
        return "我是默认值，第一次get不再是null";
    }

}

public class Run4 {

    private static ThreadLocalTest threadLocalTest = new ThreadLocalTest();

    public static void main(String[] args) {
        if (threadLocalTest.get() == null) {
            System.out.println("没有赋值过，开始赋值");
            threadLocalTest.set("赋值了");
        }

        System.out.println("已经有值了");
        System.out.println(threadLocalTest.get());
        System.out.println(threadLocalTest.get());
    }

}
