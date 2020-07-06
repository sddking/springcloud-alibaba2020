package com.order.consumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "/test")
    public String testHotKey(
            @RequestParam(value = "p1",required = false)String p1,
            @RequestParam(value = "p2",required = false)String p2
    ){
        return "-------testHotKey----------";
    }
}
