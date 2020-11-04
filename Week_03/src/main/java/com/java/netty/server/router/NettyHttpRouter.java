package com.java.netty.server.router;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;

import com.java.netty.client.NettyHttpClient;

/**
 * Created by yuchen.wu on 2020-11-04
 */

public class NettyHttpRouter extends ChannelInboundHandlerAdapter {

    public NettyHttpClient nettyHttpClient;
    private String proxyServer;
    private int proxyPort;

    public NettyHttpRouter(String proxyServer, int proxyPort) throws InterruptedException {
        nettyHttpClient = new NettyHttpClient(proxyServer, proxyPort);
        this.proxyPort = proxyPort;
        this.proxyServer = proxyServer;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest request = (FullHttpRequest) msg;
        nettyHttpClient.connect(ctx, request);
    }
}
