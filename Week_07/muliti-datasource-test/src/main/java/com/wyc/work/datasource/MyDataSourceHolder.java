package com.wyc.work.datasource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PreDestroy;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

/**
 * Created by yuchen.wu on 2020-11-29
 */
@Slf4j
@Configuration
public class MyDataSourceHolder {

    public static final String MASTER_KEY = "master";
    public static final String SLAVE_KEY = "slave";
    private static final Map<String, List<MyDataSource>> DATA_SOURCE_MAP = new ConcurrentHashMap<>();
    private static Properties PROPERTIES;

    static {
        PROPERTIES = getJdbcProperties();
        if (PROPERTIES == null) {
            log.error("jdbc properties is null");
            System.exit(0);
        }
        Set<String> keys = PROPERTIES.stringPropertyNames();
        String masterPrefixKey = null;
        Set<String> slavePrefixKeys = new HashSet<>();
        for (String key : keys) {
            if (!isJdbcConfig(key)) {
                continue;
            }
            if (isJdbcMasterConfig(key)) {
                masterPrefixKey = getJdbcPrefixConfigKey(key);
            } else if(isJdbcSlaveConfig(key)){
                slavePrefixKeys.add(getJdbcPrefixConfigKey(key));
            }
        }
        if (masterPrefixKey != null) {
            DATA_SOURCE_MAP.put(MASTER_KEY, Lists.newArrayList(getMyDataSource(masterPrefixKey)));
        }
        if (!CollectionUtils.isEmpty(slavePrefixKeys)) {
            if (slavePrefixKeys.size() == 1) {
                for (String slavePrefixKey : slavePrefixKeys) {
                    DATA_SOURCE_MAP.put(SLAVE_KEY, Lists.newArrayList(getMyDataSource(slavePrefixKey)));
                }
            } else {
                List<MyDataSource> myDataSources = new ArrayList<>(slavePrefixKeys.size());
                for (String slavePrefixKey : slavePrefixKeys) {
                    String prefixKey = slavePrefixKey;
                    myDataSources.add(getMyDataSource(prefixKey));
                }
                DATA_SOURCE_MAP.put(SLAVE_KEY, myDataSources);
            }
        }
    }

    public List<MyDataSource> getDataSource(String key) {
        return DATA_SOURCE_MAP.get(key);
    }

    @PreDestroy
    public void destroy() throws Exception {
        for (List<MyDataSource> myDataSources : DATA_SOURCE_MAP.values()) {
            for (MyDataSource myDataSource : myDataSources) {
                myDataSource.destroy();
            }
        }
    }

    private static MyDataSource getMyDataSource(String prefixKey) {
        String driver = PROPERTIES.getProperty(generateJdbcDriverKey(prefixKey));
        String url = PROPERTIES.getProperty(generateJdbcUrlKey(prefixKey));
        String username = PROPERTIES.getProperty(generateJdbcUsernameKey(prefixKey));
        String password = PROPERTIES.getProperty(generateJdbcPasswordKey(prefixKey));
        return MyDataSource.generateMyDataSource(driver, url, username, password);
    }

    private static Properties getJdbcProperties() {
        InputStream input;
        try {
            input = MyDataSource.class.getResourceAsStream("/jdbc.properties");
            Properties properties = new Properties();
            properties.load(input);
            return properties;
        } catch (Exception e) {
            log.error("get jdbc properties error");
        }
        return null;
    }

    private static String generateJdbcDriverKey(String prefixKey) {
        return prefixKey + ".driver";
    }

    private static String generateJdbcUrlKey(String prefixKey) {
        return prefixKey + ".url";
    }

    private static String generateJdbcUsernameKey(String prefixKey) {
        return prefixKey + ".username";
    }

    private static String generateJdbcPasswordKey(String prefixKey) {
        return prefixKey + ".password";
    }

    private static boolean isJdbcConfig(String key) {
        return key.startsWith("jdbc.");
    }

    private static boolean isJdbcMasterConfig(String key) {
        return key.startsWith("jdbc.master");
    }

    private static boolean isJdbcSlaveConfig(String key) {
        return key.startsWith("jdbc.slave");
    }

    private static String getJdbcPrefixConfigKey(String key) {
        return key.substring(0, key.indexOf(".", 6));
    }
}
