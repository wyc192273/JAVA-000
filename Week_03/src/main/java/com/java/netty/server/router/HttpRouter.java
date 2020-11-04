package com.java.netty.server.router;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.DefaultThreadFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.java.netty.server.filter.HttpRequestFilter;

/**
 * Created by yuchen.wu on 2020-11-03
 */

public class HttpRouter extends ChannelInboundHandlerAdapter {

    private String proxyServer;
    private int proxyPort;

    public HttpRouter(String proxyServer, int proxyPort) {
        this.proxyServer = proxyServer;
        this.proxyPort = proxyPort;
        int cores = Runtime.getRuntime().availableProcessors() * 2;
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();//.DiscardPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores, keepAliveTime, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(queueSize), new DefaultThreadFactory("proxyService"), handler);

        IOReactorConfig ioConfig = IOReactorConfig.custom()
                .setConnectTimeout(1000)
                .setSoTimeout(1000)
                .setIoThreadCount(cores)
                .setRcvBufSize(32 * 1024)
                .build();

        httpclient = HttpAsyncClients.custom()
                .setMaxConnTotal(40)
                .setMaxConnPerRoute(8)
                .setDefaultIOReactorConfig(ioConfig)
                .setKeepAliveStrategy((response, context) -> 6000)
                .build();
        httpclient.start();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest fullRequest = (FullHttpRequest) msg;
        //        System.out.println("Client received : " + fullRequest.toString());
        handle(fullRequest, ctx);
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        String url = getProxyServerUrl(fullRequest);
        proxyService.submit(() -> fetchGet(fullRequest, ctx, url));
    }

    private CloseableHttpAsyncClient httpclient;
    private ExecutorService proxyService;

    private String getProxyServerUrl(FullHttpRequest fullRequest) {
        return "http://"+proxyServer + ":" + proxyPort + fullRequest.uri();
    }

    private void fetchGet(final FullHttpRequest request, final ChannelHandlerContext ctx, final String url) {
        final HttpGet httpGet = new HttpGet(url);
        //httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
        setHttpGetHeaders(httpGet, request);
        httpclient.execute(httpGet, new FutureCallback<HttpResponse>() {

            @Override
            public void completed(final HttpResponse endpointResponse) {
                try {
                    handleResponse(request, ctx, endpointResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                }
            }

            @Override
            public void failed(final Exception ex) {
                httpGet.abort();
                ex.printStackTrace();
            }

            @Override
            public void cancelled() {
                httpGet.abort();
            }
        });
    }

    private void setHttpGetHeaders(HttpGet httpGet, FullHttpRequest request) {
        HttpHeaders httpHeaders = request.headers().copy();
        for (Map.Entry<String, String> entry : httpHeaders.entries()) {
            if (entry.getKey().equals("Host")) {
                httpGet.setHeader(entry.getKey(), proxyServer + ":" + proxyPort);
            } else {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        }
        httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
    }

    private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx,
                                final HttpResponse endpointResponse) throws Exception {
        FullHttpResponse response = null;
        try {
            //            String value = "hello,kimmking";
            //            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
            //            response.headers().set("Content-Type", "application/json");
            //            response.headers().setInt("Content-Length", response.content().readableBytes());

            byte[] body = EntityUtils.toByteArray(endpointResponse.getEntity());
            //            System.out.println(new String(body));
            //            System.out.println(body.length);

            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            response.headers().set("Content-Type", "application/json");
            response.headers()
                    .setInt("Content-Length", Integer.parseInt(endpointResponse.getFirstHeader("Content-Length").getValue()));

            //            for (Header e : endpointResponse.getAllHeaders()) {
            //                //response.headers().set(e.getName(),e.getValue());
            //                System.out.println(e.getName() + " => " + e.getValue());
            //            }

        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
            ctx.flush();
            //ctx.close();
        }

    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
