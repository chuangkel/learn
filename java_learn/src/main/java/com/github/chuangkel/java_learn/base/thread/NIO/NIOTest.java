package com.github.chuangkel.java_learn.base.thread.NIO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: javaNewCharacteristic
 * @description: new IO
 * @author: yaoqb23911
 * @create: 2018-12-28
 **/
public class NIOTest {

    private static RandomAccessFile randomAccessFile;

    static {
        try {
            randomAccessFile = new RandomAccessFile("D://test.txt", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static FileChannel fileChannel = randomAccessFile.getChannel();
    static ByteBuffer byteBuffer = ByteBuffer.allocate(48);


    public static void main(String[] args) throws IOException {

        while ((fileChannel.read(byteBuffer)) != -1) {
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                System.out.println((char) byteBuffer.get());
            }
            byteBuffer.clear();
        }
    }

    public NIOTest() throws FileNotFoundException {
    }
}
