package edu.just.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Run {

    private static Timer timer = new Timer();

    static public class MyTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("运行了, 时间为：" + new Date());
        }
    }

    public static void main(String[] args) throws ParseException {
        MyTask task = new MyTask();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = "2018-02-23 11:11:11";
        Date date = dateFormat.parse(dateString);
        System.out.println("字符串时间：" + date.toLocaleString() + " 当前时间："
                + new Date().toLocaleString());
        timer.schedule(task, date);
    }

}
