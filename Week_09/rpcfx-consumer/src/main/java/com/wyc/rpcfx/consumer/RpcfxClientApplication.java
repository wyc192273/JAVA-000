package com.wyc.rpcfx.consumer;
import java.lang.reflect.InvocationTargetException;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wyc.rpcfx.api.ProtocolFactory;
import com.wyc.rpcfx.api.Registry;
import com.wyc.rpcfx.api.RegistryDirectory;
import com.wyc.rpcfx.api.RegistryFactory;
import com.wyc.rpcfx.api.RpcfxReferenceConfig;
import com.wyc.rpcfx.api.RpcfxRegistryConfig;
import com.wyc.rpcfx.client.RpcfxProxy;
import com.wyc.rpcfx.order.api.bean.Order;
import com.wyc.rpcfx.order.api.service.OrderService;
import com.wyc.rpcfx.user.api.bean.User;
import com.wyc.rpcfx.user.api.service.UserService;

/**
 * Created by yuchen.wu on 2020-12-14
 */
@SpringBootApplication
public class RpcfxClientApplication {

    public static void main(String[] args) throws Exception {

        Registry registry = getRegistry();
        RpcfxReferenceConfig<UserService> userConfig = getReferenceCconfig(UserService.class, "http", "random"
                , "1.0.0", "test", registry);
        RpcfxReferenceConfig<OrderService> orderConfig = getReferenceCconfig(OrderService.class, "http", "random"
                , "1.0.0", "test", registry);

        UserService userService = RpcfxProxy.create(userConfig);
        User user = userService.findUserById(1);
        System.out.println("find user id=1 from server: " + user.getName());

        OrderService orderService = RpcfxProxy.create(orderConfig);
        Order order = orderService.findOrderById(1992129);
        System.out.println(String.format("find order name=%s, amount=%f",order.getName(),order.getAmount()));
    }

    private static Registry getRegistry()
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RpcfxRegistryConfig registryConfig = new RpcfxRegistryConfig();
        registryConfig.setPort(2181);
        registryConfig.setHost("127.0.0.1");
        registryConfig.setProtocol("zookeeper");
        return RegistryFactory.getRegistry(registryConfig);
    }

    private static <T> RpcfxReferenceConfig<T> getReferenceCconfig(Class<T> clazz, String protocol, String loadBalance, String version,
                                                                   String group, Registry registry) {
        RpcfxReferenceConfig<T> referenceConfig = new RpcfxReferenceConfig<>();
        referenceConfig.setProtocol(ProtocolFactory.getProtocol(protocol));
        referenceConfig.setLoadBalance(loadBalance);
        referenceConfig.setVersion(version);
        referenceConfig.setGroup(group);
        referenceConfig.setInterfaceClass(clazz);
        referenceConfig.setRegistry(registry);
        referenceConfig.setDirectory(new RegistryDirectory<>(registry));
        return referenceConfig;
    }
}
