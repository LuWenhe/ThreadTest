package edu.just.pipiao;

import java.io.IOException;
import java.io.PipedOutputStream;

/**
 * 发送线程
 *
 * PipedOutputStream: 管道输出流，向管道中写入数据
 */
public class Sender implements Runnable {

    private PipedOutputStream outputStream;

    public Sender(PipedOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        String str = "Receiver, hello world!!! nihao shijie";

        try {
            outputStream.write(str.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

}
