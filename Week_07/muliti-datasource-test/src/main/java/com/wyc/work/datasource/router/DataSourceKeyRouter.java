package com.wyc.work.datasource.router;

import java.util.List;

/**
 * Created by yuchen.wu on 2020-11-30
 */

public interface DataSourceKeyRouter {

    String routeKey(List<String> slaveDataSourceKeys);

    String getStrategyKey();

}
