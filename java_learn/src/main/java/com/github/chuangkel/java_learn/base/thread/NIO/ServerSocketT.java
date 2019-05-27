package com.github.chuangkel.java_learn.base.thread.NIO;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @program: javaNewCharacteristic
 * @description:
 * @author: yaoqb23911
 * @create: 2018-12-29
 **/
public class ServerSocketT {
    public static void main(String[] args) throws IOException {
//        ServerSocket serverSocket = new ServerSocket(9999,1);
//        Socket socket = serverSocket.accept();
//        SocketChannel channel = socket.getChannel();
//        StringBuffer stringBuffer = new StringBuffer(20);
//        ByteBuffer byteBuffer = ByteBuffer.allocate(15);
//        channel.read(byteBuffer);
//
//        Selector selector = Selector.open();
//        channel.register(selector,0,"");
//        selector.selectNow();
//        Set<SelectionKey> selectedKeys = selector.keys();
//        Iterator<SelectionKey> iterator = selectedKeys.iterator();
//        while (iterator.hasNext()){
//            SelectionKey now = iterator.next();
//            if(now.isReadable()){
//
//            }
//            iterator.remove();
//        }

        //
        Set<String> set = new HashSet<>();
        set.add("IntegerReplacement_397");
        set.add("IntegerReplacement_397");
        set.add("bb");
        set.add("cc");
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println("remove element " + iterator.next());
            iterator.remove();
        }
        System.out.println(set.size());
    }
}
