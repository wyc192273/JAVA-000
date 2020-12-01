package com.wyc.work.datasource;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.wyc.work.datasource.router.DataSourceKeyRouter;
import com.wyc.work.datasource.router.PollingDateSourceKeyRouter;

/**
 * Created by yuchen.wu on 2020-11-29
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "dynamic.data-source")
public class DynamicDataSource extends AbstractRoutingDataSource implements ApplicationContextAware {

    private String routerStrategy;
    private ApplicationContext applicationContext;
    @Resource
    private MyDataSourceHolder myDataSourceHolder;

    private final List<String> slaveDataSourceKeys = new ArrayList<>();

    private static final Map<String, DataSourceKeyRouter> ROUTER_MAP = new ConcurrentHashMap<>();

    private static final DataSourceKeyRouter DEFAULT_STRATEGY = new PollingDateSourceKeyRouter();

    private static final String MASTER_KEY = "master";

    @PostConstruct
    public void init() {
        initRouterMap();
        initTargetDataSources();
    }

    private void initRouterMap() {
        Map<String, DataSourceKeyRouter> dataSourceKeyRouterMap = applicationContext.getBeansOfType(DataSourceKeyRouter.class);
        for (DataSourceKeyRouter dataSourceKeyRouter : dataSourceKeyRouterMap.values()) {
            ROUTER_MAP.put(dataSourceKeyRouter.getStrategyKey(), dataSourceKeyRouter);
        }
    }

    private void initTargetDataSources() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        List<MyDataSource> masterDataSources = myDataSourceHolder.getDataSource(MyDataSourceHolder.MASTER_KEY);
        if (!CollectionUtils.isEmpty(masterDataSources)) {
            targetDataSources.put(MyDataSourceHolder.MASTER_KEY, masterDataSources.get(0));
        }
        List<MyDataSource> slaveDataSources = myDataSourceHolder.getDataSource(MyDataSourceHolder.SLAVE_KEY);
        if (!CollectionUtils.isEmpty(slaveDataSources)) {
            for (int i = 0; i < slaveDataSources.size(); i++) {
                String key = MyDataSourceHolder.SLAVE_KEY + i;
                targetDataSources.put(key, slaveDataSources.get(i));
                slaveDataSourceKeys.add(key);
            }
        }
        this.setTargetDataSources(targetDataSources);
    }


    @Override
    protected Object determineCurrentLookupKey() {
        if (DynamicDataSourceHolder.isMaster()) {
            return MASTER_KEY;
        }
        DataSourceKeyRouter keyRouter = ROUTER_MAP.getOrDefault(routerStrategy, DEFAULT_STRATEGY);
        return keyRouter.routeKey(slaveDataSourceKeys);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
