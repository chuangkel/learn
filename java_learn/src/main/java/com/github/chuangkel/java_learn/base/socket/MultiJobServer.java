package com.github.chuangkel.java_learn.base.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-10-18 09:24
 **/
public class MultiJobServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8081);
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new ServeOneJob(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
