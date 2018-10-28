package edu.test;

public class ThreadA extends Thread {

    private DBTools dbToolsl;

    public ThreadA(DBTools dbToolsl) {
        this.dbToolsl = dbToolsl;
    }

    @Override
    public void run() {
        dbToolsl.backupA();
    }
}
