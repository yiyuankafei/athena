package com.ahena.io.netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("server:" + body);
        String resposne = "返回给客户端响应：" + body;
        ctx.writeAndFlush(Unpooled.copiedBuffer(resposne.getBytes()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable t) {
        ctx.close();
    }

}
