package com.java.netty.client.inbound;

import java.util.Map;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

/**
 * Created by yuchen.wu on 2020-11-04
 */

public class NettyHttpInboundHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandlerContext clientCtx;

    private FullHttpRequest request;

    public NettyHttpInboundHandler(String proxyServer, int proxyPort, FullHttpRequest request, ChannelHandlerContext clientCtx) {
        HttpHeaders httpHeaders = request.headers().copy();
        for (Map.Entry<String, String> entry : httpHeaders.entries()) {
            if (entry.getKey().equals("Host")) {
                entry.setValue(proxyServer + ":" + proxyPort);
            }
        }
        FullHttpRequest re = request.copy();
        re.headers().set(httpHeaders);
        this.request = re;
        this.clientCtx = clientCtx;
    }

    public NettyHttpInboundHandler(ChannelHandlerContext clientCtx) {
        this.clientCtx = clientCtx;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        clientCtx.writeAndFlush(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.close();
    }
}
