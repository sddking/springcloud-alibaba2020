package com.order.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 180465
 * @date 2020/7/5 16:25
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderConsumerApplication {
    public static void main(String[] args){
        SpringApplication.run(OrderConsumerApplication.class,args);
    }
}
