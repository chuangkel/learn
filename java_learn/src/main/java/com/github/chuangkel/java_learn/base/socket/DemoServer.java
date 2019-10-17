package com.github.chuangkel.java_learn.base.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-10-17 19:36
 **/
public class DemoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();

        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        System.out.println(String.valueOf(bytes));
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        byteBuffer.toString();
    }
}
