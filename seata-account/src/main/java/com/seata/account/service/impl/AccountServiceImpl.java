package com.seata.account.service.impl;

import com.seata.account.dao.AccountDao;
import com.seata.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author 180465
 * @date 2020/7/7 19:19
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    public void decrease(Long userId, BigDecimal money) {
        accountDao.decrease(userId,money);
        log.info("------>扣减余额结束");
    }
}
