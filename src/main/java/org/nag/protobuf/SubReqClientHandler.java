package org.nag.protobuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SubReqClientHandler extends ChannelInboundHandlerAdapter {
    public SubReqClientHandler() {}

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.write(subReq(i));
        }

        ctx.flush();
    }

    private SubscribeReqProto subReq(int id) {
        SubscribeReqProto.Builder builder = SubscribeReqProto.newBuilder();
        builder.setSubReqID(id);
        builder.setUserName("Lilinfeng");
        builder.setProductName("Netty book For Protobuf");
        List<String> address = new ArrayList<String>();
        address.add("127.0.0.1");
        address.add("127.0.0.2");
        address.add("127.0.0.3");
        builder.addAllAddress(address);
        return builder.build();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Receive server response : [" + msg + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
