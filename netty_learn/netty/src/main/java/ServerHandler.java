import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.ByteBuffer;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-03-28 14:35
 **/
public class ServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取客户端的io信息
     * @param ctx
     * @param msg
     * @throws Exception
     */

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuffer = (ByteBuf)msg;
        byte[] req = new byte[byteBuffer.readableBytes()];
        byteBuffer.readBytes(req);
        String body = new String(req,"UTF-8");
        System.out.println("收到服务端信息："+body);
        String calrResult = null;

        try{
            calrResult = Calulator.Instance.cal(body).toString();
        }catch (Exception e){

        }finally {
            calrResult = "错误表达式";
        }
//        ctx.write(Unpooled.copiedBuffer(calrResult.getBytes()));
//        ctx.write(Unpooled.copiedBuffer("hello world".getBytes()));
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello world".getBytes()));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
