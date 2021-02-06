package com.wyc.mq.core;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yuchen.wu
 * @date 2021-01-19
 */
@AllArgsConstructor
@Data
public class WmqMessage<T> {

    private HashMap<String, Object> headers;

    private T body;

}
