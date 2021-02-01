package com.github.chuangkel.java_learn.base.thread.NIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class SocketTestServer implements Runnable {
    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            System.out.println("服务器正在启动...");
            serverSocket = new ServerSocket(9999);
            boolean flag = true;
            while (flag) {
                System.out.println("accept开始前");
                Socket cc = serverSocket.accept();//会阻塞，等到有请求进来
                System.out.println("accept开始后");
                System.out.println(cc.isConnected());
                InputStream in = cc.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
//                String temp = br.readLine();//需要有\n结尾，不然会报异常Connection reset
//                System.out.println(temp);
                char[] c = new char[24];
                while (br.read(c) != -1) {
                    System.out.println(String.valueOf(c));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
