package com.java.netty.client.outbound;

import java.net.SocketAddress;
import java.util.Map;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

/**
 * Created by yuchen.wu on 2020-11-04
 */

public class NettyHttpOutboundHandler extends ChannelOutboundHandlerAdapter {

    private FullHttpRequest request;

    public NettyHttpOutboundHandler(String proxyServer, int proxyPort, FullHttpRequest request) {

    }



}
