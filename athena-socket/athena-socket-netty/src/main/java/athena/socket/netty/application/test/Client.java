package athena.socket.netty.application.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
	
	public static void main(String[] args) throws Exception {
		
		EventLoopGroup workgroup = new NioEventLoopGroup();
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.group(workgroup)
			.channel(NioSocketChannel.class)
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel sc) throws Exception {
					sc.pipeline().addLast(new ClientHandler());
				}
			});
		
		ChannelFuture cf1 = bootstrap.connect("127.0.0.1", 8765).sync();
		//ChannelFuture cf2 = bootstrap.connect("127.0.0.1", 8765).sync();
		
		Thread.sleep(1000);
		
		cf1.channel().writeAndFlush(Unpooled.copiedBuffer("777".getBytes()));
		cf1.channel().writeAndFlush(Unpooled.copiedBuffer("666".getBytes()));
		//cf2.channel().writeAndFlush(Unpooled.copiedBuffer("888".getBytes()));
		
		Thread.sleep(2000);
		cf1.channel().writeAndFlush(Unpooled.copiedBuffer("888".getBytes()));
		//cf2.channel().writeAndFlush(Unpooled.copiedBuffer("666".getBytes()));
		
		cf1.channel().closeFuture().sync();
		//cf2.channel().closeFuture().sync();
		workgroup.shutdownGracefully();
	}

}
