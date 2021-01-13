package com.wyc.mq;

/**
 * @author yuchen.wu
 * @date 2021-01-12
 */

public interface Producer {

    void send(Object object) throws InterruptedException;

    void close();

}
