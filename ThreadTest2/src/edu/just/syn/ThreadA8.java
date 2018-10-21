package edu.just.syn;

class Main2 {

    synchronized public void serviceMethod() {
        try {
            System.out.println("int main 下一步 sleep begin "
                    + Thread.currentThread().getName() + " time = "
                    + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println("int main 下一步 sleep end "
                    + Thread.currentThread().getName() + " time = "
                    + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Sub2 extends Main2 {

    @Override
    public void serviceMethod() {
        try {
            System.out.println("int sub 下一步 sleep begin "
                    + Thread.currentThread().getName() + " time = "
                    + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println("int sub 下一步 sleep end "
                    + Thread.currentThread().getName() + " time = "
                    + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadB8 extends Thread {

    private Sub2 sub2;

    public ThreadB8(Sub2 sub2) {
        this.sub2 = sub2;
    }

    @Override
    public void run() {
        sub2.serviceMethod();
    }
}

public class ThreadA8 extends Thread{

    private Sub2 sub2;

    public ThreadA8(Sub2 sub2) {
        this.sub2 = sub2;
    }

    @Override
    public void run() {
        sub2.serviceMethod();
    }

    public static void main(String[] args) {
        Sub2 sub2 = new Sub2();
        ThreadA8 threadA8 = new ThreadA8(sub2);
        threadA8.setName("AA");
        threadA8.start();

        ThreadB8 threadB8 = new ThreadB8(sub2);
        threadB8.setName("BB");
        threadB8.start();
    }
}
