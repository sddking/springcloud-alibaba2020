package com.order.consumer.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author 180465
 * @date 2020/7/7 9:23
 */
public class CustomerFallBackHandler {
    public static String handlerNullPointerException(){
        return "自定义异常处理1";
    }
}
