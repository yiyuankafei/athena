package com.athena.socket.netty.server.application.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
	
	private final int port;
	
	public EchoServer(int port) {
		this.port = port;
	}
	
	public void start() throws Exception {
	
		final EchoServerHandler serverHandler = new EchoServerHandler();
		//1. 创建EventLoopGroup
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			//2. 创建ServerBootstrap
			ServerBootstrap b = new ServerBootstrap();
			b.group(group)
			    .channel(NioServerSocketChannel.class)  //3. 指定所使用的NIO传输Channel
			    .localAddress(new InetSocketAddress(port)) //4. 使用指定的端口设置套接字地址
			    .childHandler(new ChannelInitializer<SocketChannel>(){ //5. 添加一个EchoServerHandler到子Channel的ChannelPipeline

					@Override
					protected void initChannel(SocketChannel ch)
							throws Exception {
						ch.pipeline().addLast(serverHandler); // EchoServerHandler被标注为@Shareable，所以我们总是使用相同的实例
					}
			    });
			
			ChannelFuture f = b.bind().sync(); //6.异步绑定服务器  调用sync()方法阻塞等待直到绑定完成
			f.channel().closeFuture().sync();  //7.或者Channel的CloseFuture，并且阻塞当前线程直到他完成
			
		} finally {
			group.shutdownGracefully().sync(); //8.关闭EventLoopGroup，释放所有资源
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		new EchoServer(8882).start();
	}
	
	
}
