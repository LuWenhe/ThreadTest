package edu.just.th;

public class LoginThread {

    private static String userName;
    private static String password;

    /*public static void doPost(String username1, String password1) {
        try {
            userName = username1;
            if (username1.equals("a")) {
                Thread.sleep(5000);
            }
            password = password1;
            System.out.println("username=" + userName + " password=" + password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public synchronized static void doPost(String username1, String password1) {
        try {
            userName = username1;
            if (username1.equals("a")) {
                Thread.sleep(5000);
            }
            password = password1;
            System.out.println("username=" + userName + " password=" + password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ALogin extends Thread {
    @Override
    public void run() {
        LoginThread.doPost("a", "aa");
    }
}

class BLogin extends Thread {
    @Override
    public void run() {
        LoginThread.doPost("b", "bb");
    }
}

class Run {
    public static void main(String[] args) {
        ALogin a = new ALogin();
        a.start();
        BLogin b = new BLogin();
        b.start();
    }
}