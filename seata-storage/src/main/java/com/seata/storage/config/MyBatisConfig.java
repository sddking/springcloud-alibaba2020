package com.seata.storage.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 180465
 * @date 2020/7/7 15:43
 */
@Configuration
@MapperScan({"com.seata.storage.dao"})
public class MyBatisConfig {
}
