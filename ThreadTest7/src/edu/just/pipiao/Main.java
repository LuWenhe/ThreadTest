package edu.just.pipiao;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Main {

    public static void main(String[] args) throws IOException {
        PipedOutputStream out = new PipedOutputStream();
        PipedInputStream in = new PipedInputStream();
        out.connect(in);
//        in.connect(out);

        Sender sender = new Sender(out);
        Receiver receiver = new Receiver(in);

        Thread threadSend = new Thread(sender);
        Thread threadReceiver = new Thread(receiver);

        threadSend.start();
        threadReceiver.start();
        
        System.out.print("hello");
    }

}
