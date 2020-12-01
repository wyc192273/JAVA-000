package com.wyc.work.datasource.router;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

/**
 * Created by yuchen.wu on 2020-12-01
 */
@Component
public class RandomDataSourceKeyRouter implements DataSourceKeyRouter {

    public static final String KEY = "random";

    private static final Random RANDOM = new Random();

    @Override
    public String routeKey(List<String> slaveDataSourceKeys) {
        return slaveDataSourceKeys.get(RANDOM.nextInt(slaveDataSourceKeys.size()));
    }

    @Override
    public String getStrategyKey() {
        return KEY;
    }
}
