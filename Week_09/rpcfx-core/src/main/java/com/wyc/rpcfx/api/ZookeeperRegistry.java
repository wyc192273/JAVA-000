package com.wyc.rpcfx.api;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @author yuchen.wu
 * @date 2020-12-20
 */

public class ZookeeperRegistry extends AbstractRegistry {

    private CuratorFramework curatorFramework;

    public ZookeeperRegistry(RpcfxRegistryConfig registryConfig) throws Exception {
        super(registryConfig);
        this.curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(getConnectStr(registryConfig))
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .connectionTimeoutMs(15 * 1000)
                .sessionTimeoutMs(3 * 1000)
                .build();
        curatorFramework.start();
        if (!pathIsExist(rootPath())) {
            createPersistent(rootPath(), "init".getBytes());
        }
    }

    @Override
    public void doRegistry(RpcfxServiceConfig serviceConfig) throws Exception {
        String providerPath = getProviderPath(serviceConfig);
        if (!pathIsExist(providerPath)) {
            createEphemeral(providerPath, serviceConfig.toString().getBytes());
        }
    }

    @Override
    public void doSubscribe(RpcfxReferenceConfig rpcfxReferenceConfig) throws Exception {
        String consumerPath = getConsumerPath(rpcfxReferenceConfig);
        if (!pathIsExist(consumerPath)) {
            createEphemeral(consumerPath, rpcfxReferenceConfig.toString().getBytes());
        }
    }

    @Override
    public List<Url> getProviderUrls(RpcfxReferenceConfig rpcfxReferenceConfig) throws Exception {
        List<String> paths = getChildren(getServicePath(rpcfxReferenceConfig) + "/providers");
        if (paths == null || paths.size() == 0) {
            return new ArrayList<>();
        }
        return paths.stream().map(this::urlFromProviderPath).collect(Collectors.toList());
    }

    private Url urlFromProviderPath(String path) {
        String urlPath = path.substring(path.lastIndexOf("/") + 1);
        String[] urlDirct = urlPath.split("\\?");
        Url url = new Url();
        url.setHost(urlDirct[0].split(":")[0]);
        url.setPort(Integer.parseInt(urlDirct[0].split(":")[1]));
        String[] params = urlDirct[1].split("&");
        Map<String, String> keyValueMap = new HashMap<>();
        for (String param : params) {
            keyValueMap.put(param.split("=")[0], param.split("=")[1]);
        }
        url.setGroup(keyValueMap.get("group"));
        url.setVersion(keyValueMap.get("version"));
        return url;
    }

    private String rootPath() {
        return "/rpcfx";
    }

    private boolean pathIsExist(String path) throws Exception {
        return curatorFramework.checkExists().forPath(path) != null;
    }

    private void createPersistent(final String path, final byte[] data) throws Exception {
        curatorFramework.create().creatingParentsIfNeeded().forPath(path, data);
    }

    private void createEphemeral(final String path, final byte[] data) throws Exception {
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, data);
    }

    private void setData(final String path, final byte[] data) throws Exception {
        curatorFramework.setData().forPath(path, data);
    }

    private void delete(final String path) throws Exception {
        curatorFramework.delete().deletingChildrenIfNeeded().forPath(path);
    }

    private String getData(final String path) throws Exception {
        return new String(curatorFramework.getData().forPath(path));
    }

    private List<String> getChildren(final String path) throws Exception {
        return curatorFramework.getChildren().forPath(path);
    }

    private void destroy() {
        curatorFramework.close();
    }

    private String getServicePath(RpcfxServiceConfig serviceConfig) {
        return rootPath()
                + "/"
                + serviceConfig.getServiceName();
    }

    private String getConsumerPath(RpcfxReferenceConfig rpcfxReferenceConfig) throws UnknownHostException {
        return getServicePath(rpcfxReferenceConfig) + "/consumers/" + getConsumerNode(rpcfxReferenceConfig);
    }

    private String getProviderPath(RpcfxServiceConfig serviceConfig) {
        return getServicePath(serviceConfig) + "/providers/" + getProviderNode(serviceConfig);
    }

    private String getServicePath(RpcfxReferenceConfig rpcfxReferenceConfig) {
        return rootPath()
                + "/"
                + rpcfxReferenceConfig.getInterfaceClass().getName();
    }

    private String getConsumerNode(RpcfxReferenceConfig referenceConfig) throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress()
                + "?group="
                + referenceConfig.getGroup()
                + "&version="
                + referenceConfig.getVersion()
                + "&loadBalance="
                + referenceConfig.getLoadBalance()
                + "&protocol="
                + referenceConfig.getProtocol().getClass().getSimpleName();
    }

    private String getProviderNode(RpcfxServiceConfig serviceConfig) {
        return serviceConfig.getProviderConfig().getHost()
                + ":"
                + serviceConfig.getProviderConfig().getPort()
                + "?group="
                + serviceConfig.getGroup()
                + "&version="
                + serviceConfig.getVersion();
    }

    private String getConnectStr(RpcfxRegistryConfig registryConfig) {
        return registryConfig.getHost() + ":" + registryConfig.getPort();
    }

    public static void main(String[] args) throws Exception {
        RpcfxRegistryConfig registryConfig = new RpcfxRegistryConfig();
        registryConfig.setHost("localhost");
        registryConfig.setPort(2181);
        ZookeeperRegistry zookeeperRegistry = new ZookeeperRegistry(registryConfig);
//        testDoRegistry(zookeeperRegistry);
//        testDoSubscribe(zookeeperRegistry);
        testChildrenPath(zookeeperRegistry);
    }

    private static void testChildrenPath(ZookeeperRegistry zookeeperRegistry) throws Exception {
        System.out.println(zookeeperRegistry.getChildren("/rpcfx/com.wyc.rpcfx.order.api.service.OrderService/providers"));
    }

    private static void testDoRegistry(ZookeeperRegistry zookeeperRegistry) throws Exception {

        RpcfxServiceConfig serviceConfig = new RpcfxServiceConfig();
        serviceConfig.setGroup("test");
        serviceConfig.setVersion("1.0.0");
        serviceConfig.setServiceName("UserTest");
        RpcfxProviderConfig providerConfig = new RpcfxProviderConfig();
        providerConfig.setHost("127.0.0.1");
        providerConfig.setPort(8080);
        serviceConfig.setProviderConfig(providerConfig);
        zookeeperRegistry.doRegistry(serviceConfig);
    }

    private static void testDoSubscribe(ZookeeperRegistry zookeeperRegistry) throws Exception {
        RpcfxRegistryConfig registryConfig = new RpcfxRegistryConfig();
        registryConfig.setHost("localhost");
        registryConfig.setPort(2181);
        RpcfxReferenceConfig referenceConfig = new RpcfxReferenceConfig();
        referenceConfig.setInterfaceClass(ZookeeperRegistry.class);
        referenceConfig.setGroup("test");
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setLoadBalance("random");
        referenceConfig.setProtocol(new OkHttpClientProtocol());
        zookeeperRegistry.doSubscribe(referenceConfig);
    }
}
