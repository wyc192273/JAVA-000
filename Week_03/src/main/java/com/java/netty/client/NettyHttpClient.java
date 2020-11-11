package com.java.netty.client;

import java.util.Map;
import java.util.function.Consumer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
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
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

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
    private Bootstrap b;

    public NettyHttpClient(String proxyServer, int proxyPort) throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup(16);
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
        this.serverChannel = b.connect(proxyServer, proxyPort).channel();
        this.proxyPort = proxyPort;
        this.proxyServer = proxyServer;
        this.b = b;
    }

    public void connect(ChannelHandlerContext ctx, FullHttpRequest request) {
        Bootstrap bootstrap = b.clone();
        Channel serverChannel = bootstrap.connect(proxyServer, proxyPort).channel();
        NettyHttpOutboundHandler outboundHandler = new NettyHttpOutboundHandler(proxyServer, proxyPort, request);
        NettyHttpInboundHandler inboundHandler = new NettyHttpInboundHandler(ctx);
        Consumer<Object> consumer = o -> ctx.writeAndFlush(o);
        inboundHandler.setConsumer(consumer);

        HttpHeaders httpHeaders = request.headers().copy();
        for (Map.Entry<String, String> entry : httpHeaders.entries()) {
            if (entry.getKey().equals("Host")) {
                entry.setValue(proxyServer + ":" + proxyPort);
            }
        }
        FullHttpRequest re = request.copy();
        re.headers().set(httpHeaders);
        serverChannel.pipeline()
                .addLast(inboundHandler)
                .addLast(outboundHandler);
        serverChannel.writeAndFlush(re);

    }

}
