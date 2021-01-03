package com.wyc.rpcfx.api;

/**
 * @author yuchen.wu
 * @date 2020-12-20
 */

public class RpcfxServiceConfig {

    private String group;
    private String version;
    private String serviceName;
    private RpcfxProviderConfig providerConfig;

    public RpcfxProviderConfig getProviderConfig() {
        return providerConfig;
    }

    public void setProviderConfig(RpcfxProviderConfig providerConfig) {
        this.providerConfig = providerConfig;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
