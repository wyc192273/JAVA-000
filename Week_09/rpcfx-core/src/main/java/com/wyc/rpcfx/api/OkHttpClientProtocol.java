package com.wyc.rpcfx.api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.wyc.rpcfx.client.RpcfxServiceWrapper;

/**
 * Created by yuchen.wu on 2020-12-14
 */

public class OkHttpClientProtocol implements Protocol {

    private static final OkHttpClient OK_HTTP_CLIENT;
    static {
        ParserConfig.getGlobalInstance().addAccept("com.wyc.rpcfx");
        OK_HTTP_CLIENT = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    @Override
    public <T> RpcfxServiceWrapper<T> refer(Class<T> target, Url url) {
        return new RpcfxServiceWrapper<T>(target, url) {
            @Override
            protected RpcfxResponse invoke(RpcfxRequest req, Url url) throws RpcfxException {
                return post(req, url);
            }
        };
    }

    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

    private RpcfxResponse post(RpcfxRequest req, Url url) throws RpcfxException {
        String invokeUrl = "http://" + url.getHost() + ":" + url.getPort() + "/invoke";
        String reqJson = JSON.toJSONString(req);
//        System.out.println("req json: "+reqJson);
        final Request request = new Request.Builder()
                .url(invokeUrl)
                .post(RequestBody.create(JSONTYPE, reqJson))
                .build();
        String respJson;
        try {
            Response response = OK_HTTP_CLIENT.newCall(request).execute();
            if (response.body() == null) {
                throw new RpcfxException("response body is null");
            }
            respJson = response.body().string();
        } catch (IOException e) {
            throw new RpcfxException(e);
        }
//        System.out.println("resp json: "+respJson);
        return JSON.parseObject(respJson, RpcfxResponse.class);
    }
}
