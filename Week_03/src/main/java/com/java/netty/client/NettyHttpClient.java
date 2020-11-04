package com.java.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpServerCodec;

import com.java.netty.client.inbound.NettyHttpInboundHandler;
import com.java.netty.client.outbound.NettyHttpOutboundHandler;
import com.java.netty.server.inbound.HttpInboundHandler;

/**
 * Created by yuchen.wu on 2020-11-04
 */

public class NettyHttpClient {

    private Channel serverChannel;
    private String proxyServer;
    private int proxyPort;

    public NettyHttpClient(String proxyServer, int proxyPort) throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(workerGroup);
        b.channel(NioSocketChannel.class);
        b.option(ChannelOption.SO_KEEPALIVE, true);
        b.handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new HttpClientCodec());
                ch.pipeline().addLast(new HttpObjectAggregator(1024 * 1024));
                ch.pipeline().addLast(new HttpContentDecompressor());
            }
        });
        Channel serverChannel = b.connect(proxyServer, proxyPort).channel();
        this.serverChannel = serverChannel;
        this.proxyPort = proxyPort;
        this.proxyServer = proxyServer;
    }

    public void connect(ChannelHandlerContext ctx, FullHttpRequest request) {
        NettyHttpInboundHandler inboundHandler = new NettyHttpInboundHandler(proxyServer, proxyPort, request, ctx);
        NettyHttpOutboundHandler outboundHandler = new NettyHttpOutboundHandler(proxyServer, proxyPort, request);
        serverChannel.pipeline()
                .addLast(inboundHandler)
                .addLast(outboundHandler);
        serverChannel.writeAndFlush(request);
        serverChannel.pipeline().remove(inboundHandler).remove(outboundHandler);
    }

}
