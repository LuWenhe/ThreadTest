package edu.just.pool;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Pool {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<Integer> list = new LinkedList<>();
        ThreadPoolExecutor tpl =
                new ThreadPoolExecutor(100,
                                  100,
                                      60,
                                      TimeUnit.SECONDS,
                                  new LinkedBlockingDeque<>(20000));
        Random random = new Random();

        for (int i = 0; i < 20000; i++) {

            tpl.execute(new Runnable() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            });
        }
        tpl.shutdown();

        try {
            tpl.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        System.out.println(list.size());

    }

}
