package com.github.chuangkel.java_learn.base.socket;

import java.io.*;
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
        PrintWriter out =
                new PrintWriter(
                        new PrintWriter(
                                new BufferedWriter(
                                        new OutputStreamWriter(outputStream))),true);
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
        for(int i = 0; i< 10; i++){
            out.println("hello world"+i);
            String str = in.readLine();
            System.out.println(str);
        }
        out.println("END");
        socket.close();
    }
}
