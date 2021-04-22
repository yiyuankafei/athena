package com.athena.io.netty.server.helloworld;

import com.athena.io.netty.server.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


public class ClientStart {

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });
        ChannelFuture cf = b.connect("127.0.0.1", 9001).sync();
        //cf.channel().writeAndFlush(Unpooled.copiedBuffer("athena".getBytes()));
        cf.channel().write(Unpooled.copiedBuffer("athena".getBytes()));
        cf.channel().flush();

        cf.channel().closeFuture().sync();

        group.shutdownGracefully();
    }

}
