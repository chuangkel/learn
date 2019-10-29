package com.github.chuangkel.java_learn.base.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-10-18 09:30
 **/
public class ClientOneJob implements Runnable {
    private String name;

    public ClientOneJob(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        InetAddress address = null;
        try {
            address = InetAddress.getByName(null);
            Socket socket = new Socket(address,8081);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out =
                    new PrintWriter(
                            new BufferedWriter(
                                            new OutputStreamWriter(outputStream)),true);
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
            for(int i = 0; i< 10; i++){
                out.println(name+": hello world"+i);
                String str = in.readLine();
                System.out.println(str);
            }
            out.println("END");
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
