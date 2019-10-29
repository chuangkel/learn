package com.github.chuangkel.java_learn.base.socket;

import java.io.*;
import java.net.Socket;

/**
 * @program: learn
 * @description:
 * @author: chuangkel
 * @create: 2019-10-18 09:19
 **/
public class ServeOneJob implements Runnable{
    private Socket socket;

    public ServeOneJob(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
