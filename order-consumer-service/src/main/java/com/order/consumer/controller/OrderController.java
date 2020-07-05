package com.order.consumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author 180465
 * @date 2020/7/5 18:57
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping(value = "/get/payment/{id}")
    public String getPayment(@PathVariable("id")Integer id){
        return  restTemplate.getForObject(serverURL+"/payment/get/"+id,String.class);
    }
}
