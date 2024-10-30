package org.nag.protocolStack;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class NettyServer {
    public void bind() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                        socketChannel.pipeline().addLast("MessageEncoder", new NettyMessageEncoder());
                        socketChannel.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
                        socketChannel.pipeline().addLast("LoginAuthHandler", new LoginAuthRespHandler());
                        socketChannel.pipeline().addLast("HeartBeatHandler", new HeartBeatRespHandler());
                    }
                });
        bootstrap.bind(NettyConstant.REMOTEIP, NettyConstant.PORT).sync();
        System.out.println("Server started on port " + NettyConstant.PORT);
    }

    public static void main(String[] args) throws Exception {
        new NettyServer().bind();
    }
}
