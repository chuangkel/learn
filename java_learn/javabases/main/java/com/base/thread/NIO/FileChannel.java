package com.base.thread.NIO;


import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @program: javaNewCharacteristic
 * @description: 文件Channel
 * @author: yaoqb23911
 * @create: 2018-12-29
 **/
public class FileChannel {


    public static void main(String[] args) throws IOException {
        RandomAccessFile r1 = new RandomAccessFile("D://test.txt","rw");
        java.nio.channels.FileChannel fromChannel = r1.getChannel();

        RandomAccessFile r2 = new RandomAccessFile("D://aabb.txt","rw");
        java.nio.channels.FileChannel toChannel = r2.getChannel();

        long position = 0L;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel,position,count);



    }
}
