package com.wyc.rpcfx.api;

/**
 * @author yuchen.wu
 * @date 2020-12-20
 */

public class RpcfxReferenceConfig<T> {

    /**
     * 使用的 调用协议
     */
    private Protocol protocol;
    /**
     * 接口
     */
    private Class<T> interfaceClass;
    /**
     * 分组
     */
    private String group;
    /**
     * 版本号
     */
    private String version;
    /**
     * 负载均衡
     */
    private String loadBalance = "random";
    /**
     * 注册中心
     */
    private Registry registry;

    private Directory<T> directory;

    public void setInterfaceClass(Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public Class<T> getInterfaceClass() {
        return interfaceClass;
    }

    public Directory<T> getDirectory() {
        return directory;
    }

    public void setDirectory(Directory<T> directory) {
        this.directory = directory;
    }

    public Registry getRegistry() {
        return registry;
    }

    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
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

    public String getLoadBalance() {
        return loadBalance;
    }

    public void setLoadBalance(String loadBalance) {
        this.loadBalance = loadBalance;
    }
}
