package com.wyc.rpcfx.api;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.wyc.rpcfx.client.RpcfxServiceWrapper;

/**
 * Created by yuchen.wu on 2020-12-14
 */

public class OkHttpClientProtocol implements Protocol {

    static {
        ParserConfig.getGlobalInstance().addAccept("com.wyc.rpcfx");
    }

    @Override
    public <T> RpcfxServiceWrapper<T> refer(Class<T> target, String url) {
        return new RpcfxServiceWrapper<T>(target, url) {
            @Override
            protected RpcfxResponse invoke(RpcfxRequest req, String url) throws RpcfxException {
                return post(req, url);
            }
        };
    }

    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

    private RpcfxResponse post(RpcfxRequest req, String url) throws RpcfxException {
        String reqJson = JSON.toJSONString(req);
        System.out.println("req json: "+reqJson);

        // 1.可以复用client
        // 2.尝试使用httpclient或者netty client
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(JSONTYPE, reqJson))
                .build();
        String respJson;
        try {
            respJson = client.newCall(request).execute().body().string();
        } catch (IOException e) {
            throw new RpcfxException(e);
        }
        System.out.println("resp json: "+respJson);
        return JSON.parseObject(respJson, RpcfxResponse.class);
    }
}
