package com.wyc.mq;

/**
 * @author yuchen.wu
 * @date 2021-01-12
 */

public interface Consumer {

    void consume();

    void close();

}
