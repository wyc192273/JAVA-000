package com.wyc.work.datasource.router;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

/**
 * Created by yuchen.wu on 2020-12-01
 */
@Component
public class PollingDateSourceKeyRouter implements DataSourceKeyRouter {

    public static final String KEY = "polling";

    private AtomicInteger counter = new AtomicInteger();

    @Override
    public String routeKey(List<String> slaveDataSourceKeys) {
        int index = counter.incrementAndGet() % slaveDataSourceKeys.size();
        if (counter.get() > 9999) {
            counter.set(1);
        }
        return slaveDataSourceKeys.get(index);
    }

    @Override
    public String getStrategyKey() {
        return KEY;
    }

}
