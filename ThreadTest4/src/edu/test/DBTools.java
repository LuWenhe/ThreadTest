package edu.test;

/**
 * 调用前 isFlag 为 true，因此线程 AAA 先进入 wait，然后执行线程 BBB 的 backupB 的 while 方法块后面的方法，
 * 输出 ^^^^^ 后将 isFlag 置为 false，执行 notifyAll() 方法，将线程 AAA 唤醒；
 *
 * 线程 AAA 开始执行，首先跳出 while 循环，然后输出 *****，将 isFlag 置为 true，然后再次执行线程 BBB 的 backupB
 * 方法，输出 while 语句块后面的语句
 */
public class DBTools {

    private boolean isFlag = true;

    synchronized public void backupA() {
        try {
            while (isFlag == true) {
                wait();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("*****");
            }
            isFlag = true;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void backupB() {
        try {
            while (isFlag == false) {
                wait();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("^^^^^");
            }
            isFlag = false;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
