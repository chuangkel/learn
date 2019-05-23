import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-03-28 14:45
 **/
public class NettyClient implements Runnable{

    static ClientHandler client = new ClientHandler();

    public void run() {
        String host = "127.0.0.1";
        int port = 9090;
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();


        try{
            Bootstrap b  = new Bootstrap();
            b.group(eventLoopGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE,true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(client);
                }
            });
            ChannelFuture f = b.connect(host,port).sync();
            f.channel().closeFuture().sync();
        }catch (Exception e){

        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new Thread(new NettyClient()).start();
        Scanner scanner = new Scanner(System.in);
        while (client.sendMsg(scanner.nextLine())){};
    }
}
