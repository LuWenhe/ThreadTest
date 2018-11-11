package edu.just.pipiao;

import java.io.IOException;
import java.io.PipedInputStream;

/**
 * 接受数据的线程
 *
 * PipedInputStream：管道输入流，从管道中读取数据
 */
public class Receiver implements Runnable {

    private PipedInputStream inputStream;

    public Receiver(PipedInputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        byte[] buffers = new byte[10];
        int len;

        try {
            while ((len = inputStream.read(buffers)) != -1) {
                for (int i = 0; i < len; i++) {
                    System.out.print((char) buffers[i]);
                }
//                System.out.print(new String(buffers,0,len));
            }

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
