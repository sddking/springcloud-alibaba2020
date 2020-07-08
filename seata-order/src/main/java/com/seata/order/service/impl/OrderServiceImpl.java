package com.seata.order.service.impl;

import com.seata.order.dao.OrderDao;
import com.seata.order.domain.Order;
import com.seata.order.service.AccountService;
import com.seata.order.service.OrderService;
import com.seata.order.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 180465
 * @date 2020/7/7 15:23
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    /**
     * name为任意唯一的名称
     * @param order
     */
    @GlobalTransactional(name = "order-create",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("------>开始新建订单");
        orderDao.create(order);

        log.info("------>订单微服务开始调用库存服务，执行扣减");
        storageService.decrease(order.getProductId(),order.getCount());

        log.info("------------->订单微服务开始调用账户服务,账户余额的扣减");
        accountService.decrease(order.getUserId(),order.getMoney());

        log.info("------------->修改订单的状态");
        orderDao.update(order.getUserId(),0);

        log.info("------------->下订单完成");
    }
}
