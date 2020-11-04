package com.java.netty.server;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import com.java.netty.server.filter.HttpNioHeaderRequestFilter;
import com.java.netty.server.filter.HttpRequestFilter;
import com.java.netty.server.inbound.ClientRegistry;
import com.java.netty.server.inbound.HttpInboundHandler;
import com.java.netty.server.outbound.HttpOutboundHandler;
import com.java.netty.server.router.HttpRouter;
import com.java.netty.server.router.NettyHttpRouter;

/**
 * Created by yuchen.wu on 2020-11-03
 */

public class HttpInBoundInitializer extends ChannelInitializer<SocketChannel> {

    private String proxyServer;
    private int proxyPort;

    public HttpInBoundInitializer(String proxyServer, int proxyPort) {
        this.proxyServer = proxyServer;
        this.proxyPort = proxyPort;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        List<HttpRequestFilter> filters = new ArrayList<>();
        filters.add(new HttpNioHeaderRequestFilter());
        ChannelPipeline p = socketChannel.pipeline();
        p.addLast(new HttpServerCodec());
        p.addLast(new HttpObjectAggregator(1024 * 1024));
        p.addLast(new ClientRegistry());
        p.addLast(new HttpInboundHandler(filters));
//        p.addLast(new NettyHttpRouter(proxyServer, proxyPort));
        p.addLast(new HttpRouter(proxyServer, proxyPort));
        p.addLast(new HttpOutboundHandler());
    }


}
