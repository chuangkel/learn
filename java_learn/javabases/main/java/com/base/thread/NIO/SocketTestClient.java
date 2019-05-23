package com.base.thread.NIO;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.channels.SocketChannel;

/**
 * @program: javaNewCharacteristic
 * @description:
 * @author: yaoqb23911
 * @create: 2019-01-02
 **/
public class SocketTestClient implements Runnable {
    private String str;
    SocketTestClient(String str){
        this.str = str;
    }

    @Override
    public void run() {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1",9999);
            SocketChannel channel = socket.getChannel();
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            printStream.print(str);
//        OutputStream os = socket.getOutputStream();
//        os.write("fffff".getBytes());
//        os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
