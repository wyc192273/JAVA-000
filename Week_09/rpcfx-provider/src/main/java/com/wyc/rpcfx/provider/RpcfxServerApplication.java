package com.wyc.rpcfx.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wyc.rpcfx.api.Registry;
import com.wyc.rpcfx.api.RegistryFactory;
import com.wyc.rpcfx.api.RpcfxProviderConfig;
import com.wyc.rpcfx.api.RpcfxRegistryConfig;
import com.wyc.rpcfx.api.RpcfxServiceConfig;
import com.wyc.rpcfx.order.api.service.OrderService;
import com.wyc.rpcfx.user.api.service.UserService;

/**
 * Created by yuchen.wu on 2020-12-14
 */
@SpringBootApplication
public class RpcfxServerApplication {

    public static void main(String[] args) throws Exception {
        RpcfxRegistryConfig registryConfig = new RpcfxRegistryConfig();
        registryConfig.setPort(2181);
        registryConfig.setHost("127.0.0.1");
        registryConfig.setProtocol("zookeeper");
        Registry registry = RegistryFactory.getRegistry(registryConfig);
        registryProvider(registry);
        SpringApplication.run(RpcfxServerApplication.class, args);
    }

    public static void registryProvider(Registry registry) throws Exception {
        RpcfxProviderConfig providerConfig = new RpcfxProviderConfig();
        providerConfig.setHost("127.0.0.1");
        providerConfig.setPort(8081);
        RpcfxServiceConfig orderServiceConfig = new RpcfxServiceConfig();
        orderServiceConfig.setProviderConfig(providerConfig);
        orderServiceConfig.setServiceName(OrderService.class.getName());
        orderServiceConfig.setVersion("1.0.0");
        orderServiceConfig.setGroup("test");
        RpcfxServiceConfig userServiceConfig = new RpcfxServiceConfig();
        userServiceConfig.setProviderConfig(providerConfig);
        userServiceConfig.setServiceName(UserService.class.getName());
        userServiceConfig.setVersion("1.0.0");
        userServiceConfig.setGroup("test");
        registry.doRegistry(userServiceConfig);
        registry.doRegistry(orderServiceConfig);
    }

}
