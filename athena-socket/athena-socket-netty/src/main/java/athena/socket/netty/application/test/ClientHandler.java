package athena.socket.netty.application.test;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ClientHandler extends ChannelHandlerAdapter {
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//((ByteBuf)msg).release();
		ByteBuf buf = (ByteBuf)msg;
		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);
		System.out.println("client" + new String(bytes, "UTF-8"));
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
	}

}
