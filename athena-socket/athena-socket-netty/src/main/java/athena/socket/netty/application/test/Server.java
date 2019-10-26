package athena.socket.netty.application.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class Server {
	
	public static void main(String[] args) throws Exception {
		//NioEventLoopGroup 用来处理I/O操作的多线程事件循环器
		//1.用来接收Client的连接
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		//2.用来处理已经接收进来的连接，进行实际的业务处理
		EventLoopGroup workGroup = new NioEventLoopGroup();
		
		//3.创建一个辅助类，对我们的Server进行一系列配置
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(bossGroup, workGroup) 				//把两个线程组加入进来
			.channel(NioServerSocketChannel.class) 			//使用什么通道，这里是NioServerSocketChannel (用于TCP协议)
			.childHandler(new ChannelInitializer<SocketChannel>() { 	//绑定具体的事件处理器
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					//设置分隔符解决拆包粘包
					//ByteBuf limitBuf = Unpooled.copiedBuffer("$_".getBytes());
					//ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, limitBuf));
					//ch.pipeline().addLast(new StringDecoder());
					//定长
					//ch.pipeline().addLast(new FixedLengthFrameDecoder(5));
					//序列化
					//ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
					//ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
					//一定时间没有数据，主动断开连接   -----客户端和服务器都要有
					//ch.pipeline().addLast(new ReadTimeoutHandler(5));
					ch.pipeline().addLast(new ServerHandler());    //
				}
			})
			/**
			 * 服务器端TCP内核模块维护两个队列A和B
			 * 客户端向服务器connect的时候，会发送带有SYN标志的包（第一次握手）
			 * 服务器收到客户端的SYN包，向客户端发送SYN ACK确认（第二次握手）
			 * 此时TCP内核把客户端的连接放入A队列，然后服务器收到客户端的ACK（第三次握手）
			 * TCP内核把客户端从A队列移到B队列，连接完成，应用程序的accept会返回
			 * 也就是说accept从B队列取出完成三次握手的连接
			 * A队列和B队列的长度之和是backlog，当其和大于backlog时，客户端连接会被TCP内核拒绝
			 * 所以，若backlog过小，会导致accept速度跟不上，AB队列满了导致客户端无法连接
			 * 要注意的是：backlog对程序支持的并发数没有影响，backlog影响的只是还没有被accept取出的连接
			 */
			.option(ChannelOption.SO_BACKLOG, 128)			//设置TCP连接缓冲区
			.option(ChannelOption.SO_KEEPALIVE, true);		//保持连接
			
		ChannelFuture f = bootstrap.bind(8765).sync();      //绑定指定的端口进行监听
		
		f.channel().closeFuture().sync(); //阻塞 相当于Thread.sleep(xxxxx)
		
		bossGroup.shutdownGracefully();
		workGroup.shutdownGracefully();
	}
}
