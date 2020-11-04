package com.java.netty.server.inbound;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.List;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

import com.java.netty.server.filter.HttpRequestFilter;
import com.java.netty.server.router.HttpRouter;

/**
 * Created by yuchen.wu on 2020-11-03
 */

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private List<HttpRequestFilter> filters;

    public HttpInboundHandler(List<HttpRequestFilter> filters) {
        this.filters = filters;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest fullRequest = (FullHttpRequest) msg;
        for (HttpRequestFilter filter : filters) {
            filter.filter(fullRequest, ctx);
        }
//        System.out.println("Client received : " + fullRequest.toString());
        ctx.fireChannelRead(msg);
    }

}
