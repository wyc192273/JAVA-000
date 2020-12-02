package com.wyc.work.datasource;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;

import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.properties.ConfigurationPropertyKey;
import org.apache.shardingsphere.replicaquery.api.config.ReplicaQueryRuleConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.rule.ReplicaQueryDataSourceRuleConfiguration;

/**
 * Created by yuchen.wu on 2020-12-02
 */

public class DatasourceFactory {

    public static DataSource newInstance() throws SQLException, IOException {
        ReplicaQueryDataSourceRuleConfiguration dataSourceConfig = new ReplicaQueryDataSourceRuleConfiguration(
                "pr-ds", "demo_primary_ds", Arrays.asList("demo_replica_ds_0", "demo_replica_ds_1"), "ROUND_ROBIN");
        ReplicaQueryRuleConfiguration ruleConfig = new ReplicaQueryRuleConfiguration(Collections.singleton(dataSourceConfig),
                Collections.emptyMap());
        Properties properties = new Properties();
        properties.setProperty(ConfigurationPropertyKey.SQL_SHOW.getKey(), "true");
        return ShardingSphereDataSourceFactory.createDataSource(createDataSourceMap(), Collections.singleton(ruleConfig), properties);
    }

    private static Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>(3, 1);
        result.put("demo_primary_ds", DataSourceUtil.createDataSource("test_master"));
        result.put("demo_replica_ds_0", DataSourceUtil.createDataSource("test_slave"));
        result.put("demo_replica_ds_1", DataSourceUtil.createDataSource("test_slave2"));
        return result;
    }

}
