package edu.stream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

class WriteData {

    public void writeMethod(PipedOutputStream outputStream) {
        System.out.println("write：");
        try {
            for (int i = 0; i < 300; i++) {
                String outData = "" + (i + 1);
                outputStream.write(outData.getBytes());
                System.out.println(outData);
            }

            System.out.println();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class ReadData {

    public void readMethod(PipedInputStream inputStream) {
        try {
            System.out.println("read：");
            byte[] buffer = new byte[20];
            int read = inputStream.read(buffer);
            while (read != -1) {
                String newData = new String(buffer, 0, read);
                System.out.println(newData);
                read = inputStream.read(buffer);
            }
            System.out.println();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

public class Run {
}
