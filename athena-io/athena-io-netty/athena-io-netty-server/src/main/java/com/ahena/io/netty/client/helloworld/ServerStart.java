package com.ahena.io.netty.client.helloworld;

import com.ahena.io.netty.client.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerStart {

    public static void main(String[] args) throws Exception {
        EventLoopGroup pGroup = new NioEventLoopGroup();                    //用于接收客户端链接
        EventLoopGroup cGroup = new NioEventLoopGroup();                    //用于处理读写事件

        ServerBootstrap b = new ServerBootstrap();                          //创建辅助工具类，用于服务器通道的一系列配置

        b.group(pGroup, cGroup)                                     //绑定两个线程组
                .channel(NioServerSocketChannel.class)              //指定NIO模式
                .option(ChannelOption.SO_BACKLOG, 1024)      //设置TCP缓冲区
                .option(ChannelOption.SO_SNDBUF, 32 * 1024)  //设置发送缓冲区大小
                .option(ChannelOption.SO_RCVBUF, 32 * 1024)  //设置接收缓冲区大小
                .option(ChannelOption.SO_KEEPALIVE, true)    //保持长连接
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ServerHandler());     //配置具体数据接收方法的处理
                    }
                });

        ChannelFuture cf = b.bind(9001).sync();     //监听端口
        cf.channel().closeFuture().sync();                   //等待关闭  可以理解为Thread.sleep(maxValue),让程序阻塞在这里，不会让main方法结束而关闭
        pGroup.shutdownGracefully();  //管道关闭，资源释放
        cGroup.shutdownGracefully();  //管道关闭，资源释放
    }


}
