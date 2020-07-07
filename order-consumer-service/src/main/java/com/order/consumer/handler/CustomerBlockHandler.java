package com.order.consumer.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author 180465
 * @date 2020/7/7 8:50
 */
public class CustomerBlockHandler {
    public static String handlerExceptionNoHotKey(BlockException exception){
        return "自定义异常处理1";
    }

    public static String handlerExceptionNoCircuit(BlockException exception){
        return "自定义异常处理2";
    }
}
