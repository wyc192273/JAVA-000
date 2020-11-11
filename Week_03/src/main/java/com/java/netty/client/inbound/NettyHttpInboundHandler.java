package com.java.netty.client.inbound;

import java.util.Map;
import java.util.function.Consumer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

/**
 * Created by yuchen.wu on 2020-11-04
 */

public class NettyHttpInboundHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandlerContext clientCtx;
    private Consumer<Object> consumer;

    public NettyHttpInboundHandler(ChannelHandlerContext clientCtx) {
        this.clientCtx = clientCtx;
    }

    public void setConsumer(Consumer<Object> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        consumer.accept(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.close();

    }
}
