package com.github.chuangkel.java_learn.base.classloader;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-09-09 20:41
 **/
public class CostomClassLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File("D:/Person.class");
        byte[] fileBytes = getClassBytes(file);
        Class<?> c = this.defineClass(name,fileBytes,0,fileBytes.length);
         return c;
    }

    private byte[] getClassBytes(File file) {
        FileInputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            is = new FileInputStream(file);
            FileChannel fc = is.getChannel();
            baos = new ByteArrayOutputStream();
            WritableByteChannel channel = Channels.newChannel(baos);

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while(true){
                 int i = fc.read(byteBuffer);
                 if (i == 0 || i == -1){
                     break;
                 }
                 byteBuffer.flip();
                 channel.write(byteBuffer);
                 byteBuffer.clear();
            }
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
        return baos.toByteArray();
    }
}
