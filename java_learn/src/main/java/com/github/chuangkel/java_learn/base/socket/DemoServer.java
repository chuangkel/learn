package com.github.chuangkel.java_learn.base.socket;

import java.io.*;
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
        BufferedReader in =
                new BufferedReader(new InputStreamReader(inputStream));
        PrintWriter out =
                new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(socket.getOutputStream())),true);
        while (true){
            String hello = in.readLine();
            System.out.println(hello);
            out.println(hello);
            if("END".equals(hello)) break;
        }
    }
}
