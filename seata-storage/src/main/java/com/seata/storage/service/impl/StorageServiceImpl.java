package com.seata.storage.service.impl;

import com.seata.storage.dao.StorageDao;
import com.seata.storage.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 180465
 * @date 2020/7/7 19:29
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    public void decrease(Long productId, Integer count) {
        storageDao.decrease(productId,count);
        log.info("------>扣减库存结束");
    }
}
