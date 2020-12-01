package com.wyc.work.datasource;

/**
 * Created by yuchen.wu on 2020-11-29
 */

public class DynamicDataSourceHolder {

    //写库对应的数据源key
    private static final String MASTER = "master";

    //读库对应的数据源key
    private static final String SLAVE = "slave";

    //使用ThreadLocal记录当前线程的数据源key
    private static final ThreadLocal<String> HOLDER = new ThreadLocal<String>();

    /**
     * 设置数据源key
     * @param key
     */
    public static void putDataSourceKey(String key) {
        HOLDER.set(key);
    }

    /**
     * 获取数据源key
     * @return
     */
    public static String getDataSourceKey() {
        return HOLDER.get();
    }

    /**
     * 是否是写库
     */
    public static boolean isMaster() {
        return MASTER.equals(getDataSourceKey());
    }

    /**
     * 标记写库
     */
    public static void markMaster(){
        putDataSourceKey(MASTER);
    }

    /**
     * 标记读库
     */
    public static void markSlave(){
        putDataSourceKey(SLAVE);
    }
}
