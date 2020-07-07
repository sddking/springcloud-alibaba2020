package com.seata.order.controller;

import com.seata.order.domain.CommonResult;
import com.seata.order.domain.Order;
import com.seata.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 180465
 * @date 2020/7/7 15:40
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Order order){
        orderService.create(order);
        return new CommonResult(200,"订单创建成功!");
    }
}
