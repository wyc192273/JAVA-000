package com.java.netty.server.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import org.apache.http.client.HttpClient;

/**
 * Created by yuchen.wu on 2020-11-04
 */

public class HttpNioHeaderRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("nio", "wuyuchen");
    }
}
