package com.seata.account.service;

import java.math.BigDecimal;

public interface AccountService {
    public void decrease(Long userId, BigDecimal money);
}
