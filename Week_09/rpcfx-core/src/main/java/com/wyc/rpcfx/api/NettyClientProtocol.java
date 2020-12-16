package com.wyc.rpcfx.api;

import com.wyc.rpcfx.client.RpcfxServiceWrapper;

/**
 * Created by yuchen.wu on 2020-12-14
 */

public class NettyClientProtocol implements Protocol{

    @Override
    public <T> RpcfxServiceWrapper<T> refer(Class<T> target, String url) {
        return new RpcfxServiceWrapper<T>(target, url) {
            @Override
            protected RpcfxResponse invoke(RpcfxRequest req, String url) throws RpcfxException {
//                post(req, url);
                return null;
            }
        };
    }

//        private RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
//            String reqJson = JSON.toJSONString(req);
//            System.out.println("req json: "+reqJson);
//
//            // 1.可以复用client
//            // 2.尝试使用httpclient或者netty client
//            OkHttpClient client = new OkHttpClient();
//            final Request request = new Request.Builder()
//                    .url(url)
//                    .post(RequestBody.create(JSONTYPE, reqJson))
//                    .build();
//            String respJson = client.newCall(request).execute().body().string();
//            System.out.println("resp json: "+respJson);
//            return JSON.parseObject(respJson, RpcfxResponse.class);
//        }

}
