package com.seata.storage.controller;

import com.seata.storage.domain.CommonResult;
import com.seata.storage.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 180465
 * @date 2020/7/7 19:26
 */
@RestController
@RequestMapping(value = "/storage")
public class StorageController {

    @Resource
    private StorageService storageService;

    @GetMapping(value = "/decrease")
    public CommonResult decrease(@RequestParam("productId")Long productId,@RequestParam("count")Integer count){
        storageService.decrease(productId,count);
        return new CommonResult(200,"库存扣减成功!");
    }
}
