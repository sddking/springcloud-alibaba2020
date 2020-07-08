package com.seata.account.controller;

import com.seata.account.domain.CommonResult;
import com.seata.account.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author 180465
 * @date 2020/7/7 19:09
 */
@RestController
@RequestMapping(value = "/account")
public class AccounController {

    @Resource
    private AccountService accountService;

    @GetMapping(value = "/decrease")
    public CommonResult decrease(@RequestParam("userId")Long userId, @RequestParam("money")BigDecimal money){
        accountService.decrease(userId, money);
        return new CommonResult(200,"扣减账户余额成功!");
    }
}
