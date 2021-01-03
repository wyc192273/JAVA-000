package com.wyc.rpcfx.api;

/**
 * 注册中心 配置
 * @author yuchen.wu
 * @date 2020-12-20
 */

public class RpcfxRegistryConfig {

    private String protocol;
    private String host;
    private int port;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
