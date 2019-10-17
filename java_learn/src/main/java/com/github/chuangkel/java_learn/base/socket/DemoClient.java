package com.github.chuangkel.java_learn.base.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-10-17 19:39
 **/
public class DemoClient {
    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getByName(null);
        Socket socket = new Socket(address,8080);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello world".getBytes());
    }
}
