import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @program: javabase
 * @description:
 * @author: chuangkel
 * @create: 2019-03-28 15:26
 **/
public class ClientHandler extends ChannelInboundHandlerAdapter {
    ChannelHandlerContext ctx;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
    }

    public boolean sendMsg(String msg){
        System.out.println("客户端发送消息：" + msg);
        byte[] req  = msg.getBytes();
        ByteBuf byteBuf = Unpooled.buffer(req.length);
        byteBuf.writeBytes(req);
        ctx.writeAndFlush(byteBuf);
        return "q".equals(new String(req)) ? false : true;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        String result = new String(req,"UTF-8");
        System.out.println("服务器消息：" + result);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
