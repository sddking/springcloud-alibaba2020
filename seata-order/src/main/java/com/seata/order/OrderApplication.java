package com.seata.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 180465
 * @date 2020/7/7 14:49
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消druid数据源的自动创建，使用seata的数据源绑定设置
@EnableFeignClients
@EnableDiscoveryClient
public class OrderApplication {
    public static void main(String[] args){
        SpringApplication.run(OrderApplication.class,args);
    }
}
