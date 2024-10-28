package org.nag.serial;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.nag.protobuf.SubscribeReqProto;
import org.nag.protobuf.SubscribeRespProto;

public class SubReqServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReqProto req = (SubscribeReqProto) msg;
        if ("Lilinfeng".equalsIgnoreCase(req.getUserName())) {
            System.out.println("Service accept client subscrib req : [" + req.toString() + "]");
            ctx.writeAndFlush(resp(req.getSubReqID()));
        }
    }

    private SubscribeRespProto resp(int subReqID) {
        SubscribeRespProto.Builder build = SubscribeRespProto.newBuilder();
        build.setSubReqID(subReqID);
        build.setRespCode(0);
        build.setDesc("Netty book order succeed, 3 days later, sent to the designated address");
        return build.build();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();// 发生异常，关闭链路
    }
}