package edu.just.pool;


import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class UnPool {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<Integer> list = new LinkedList<>();
        Random random = new Random();

        for (int i = 0; i < 20000; i++) {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            };
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        System.out.println(list.size());
    }

}