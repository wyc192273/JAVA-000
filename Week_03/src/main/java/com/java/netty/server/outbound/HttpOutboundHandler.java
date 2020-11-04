package com.java.netty.server.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class HttpOutboundHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
//        System.out.println("write");
        ctx.write(msg, promise);

    }

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("read");
        ctx.read();
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("flush");
        ctx.flush();
    }
}
