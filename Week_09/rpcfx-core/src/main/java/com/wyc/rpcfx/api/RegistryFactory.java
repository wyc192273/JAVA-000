package com.wyc.rpcfx.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuchen.wu
 * @date 2020-12-21
 */
public class RegistryFactory {

    private static final Map<String, Class<? extends AbstractRegistry>> REGISTRY_CLASS_MAP = new HashMap<>();
    private static final Map<String, Registry> REGISTRY_MAP = new HashMap<>();

    static {
        REGISTRY_CLASS_MAP.put("zookeeper", ZookeeperRegistry.class);
    }

    public static Registry getRegistry(RpcfxRegistryConfig registryConfig)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (REGISTRY_MAP.containsKey(registryConfig.getProtocol())) {
            return REGISTRY_MAP.get(registryConfig.getProtocol());
        }
        Class<? extends AbstractRegistry> clazz = REGISTRY_CLASS_MAP.get(registryConfig.getProtocol());
        if (clazz == null) {
            System.out.println("registry protocol no support");
            System.exit(0);
        }
        Constructor<? extends AbstractRegistry> constructor = clazz.getConstructor(RpcfxRegistryConfig.class);
        Registry registry = constructor.newInstance(registryConfig);
        REGISTRY_MAP.put(registryConfig.getProtocol(), registry);
        return registry;
    }

}
