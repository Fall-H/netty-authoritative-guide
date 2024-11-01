package org.nag.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {
    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("The time server receive order : " + body + "; the counter is : " + ++counter);
        String currentTime = "Query time order".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "bad order";
        currentTime = currentTime + System.lineSeparator();
        ByteBuf buf = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(buf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}