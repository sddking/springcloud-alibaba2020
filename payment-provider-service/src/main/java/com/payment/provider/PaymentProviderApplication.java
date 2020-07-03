package com.payment.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 180465
 * @date 2020/7/3 15:11
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentProviderApplication {
    public static void main(String[] args){
        SpringApplication.run(PaymentProviderApplication.class,args);
    }
}
