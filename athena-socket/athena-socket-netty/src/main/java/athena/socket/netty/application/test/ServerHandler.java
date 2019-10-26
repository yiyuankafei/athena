package athena.socket.netty.application.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ServerHandler extends ChannelHandlerAdapter {
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//((ByteBuf)msg).release();
		try {
			ByteBuf buf = (ByteBuf)msg;
			byte[] bytes = new byte[buf.readableBytes()];
			buf.readBytes(bytes);
			System.out.println("Server -> " + new String(bytes, "UTF-8"));
			
			//写给客户端
			ctx.writeAndFlush(Unpooled.copiedBuffer(("我是反馈信息:" + new String(bytes, "UTF-8")).getBytes()));
			
			//短连接,客户端接收消息后，服务端关闭连接
			//ctx.writeAndFlush(Unpooled.copiedBuffer(("我是反馈信息:" + new String(bytes, "UTF-8")).getBytes())).addListener(ChannelFutureListener.CLOSE);
			
			//ctx.writeAndFlush(Unpooled.copiedBuffer(("我是反馈信息:" + new String(bytes, "UTF-8")).getBytes())).addListener(ChannelFutureListener.CLOSE);
			
			/*ChannelFuture future = ctx.writeAndFlush(Unpooled.copiedBuffer(("我是反馈信息:" + new String(bytes, "UTF-8")).getBytes()));
			future.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture arg0) throws Exception {
					if (future == arg0) {
						ctx.close();
					}
				}
			});*/
			
			
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
	}

}
