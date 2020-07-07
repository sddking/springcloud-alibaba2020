package com.order.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.order.consumer.handler.CustomerBlockHandler;
import com.order.consumer.handler.CustomerFallBackHandler;
import com.order.consumer.service.PaymentService;
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
//    @Resource
//    private RestTemplate restTemplate;

//    @Value("${service-url.nacos-user-service}")
//    private String serverURL;

//    @GetMapping(value = "/get/payment/{id}")
//    public String getPayment(@PathVariable("id")Integer id){
//        return  restTemplate.getForObject(serverURL+"/payment/get/"+id,String.class);
//    }

    @Resource
    private PaymentService paymentService;

    //供热点规则的资源名使用
    //当不满足热点规则时，由blockHandler兜底
    //参数索引从零开始,例如sentinel中设置的阈值为1且参数的索引设置为0，当QPS超过1时则立即触发BlockException
    // localhost:8003/testHotKey?p1=a           受限制
    // localhost:8003/testHotKey?p1=a&p2=b      受限制
    // localhost:8003/testHotKey?p2=b           不受限制
    @GetMapping(value = "/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(
            @RequestParam(value = "p1",required = false)String p1,
            @RequestParam(value = "p2",required = false)String p2
    ){
        return "-------testHotKey----------";
    }
    public String deal_testHotKey(String p1, String p2, BlockException blockException){
        return "................哭脸";
    }

    @GetMapping(value = "/testCommonRequest")
    @SentinelResource(value = "testCommonRequest",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerExceptionNoHotKey")
    public String testCommonRequest(
    ){
        return "-------testCommonRequest----------";
    }

    /**
     * fallback只管程序异常配置
     * blockHandler只管限流异常配置
     * @return
     */
    @GetMapping(value = "/testFallBack")
    @SentinelResource(value = "testFallBack",fallback = "fallBackHandler")
    public String testFallBack(
    ){
        int a = 10/0;
        return "-------testCommonRequest----------";
    }

    public String fallBackHandler(){
        return  "fallback";
    }

    //两者都配置时，触发blockHandler后，只会进入到blockHandlerException
    //忽略属性exceptionsToIgnore
    @GetMapping(value = "/testBlockFallBack")
    @SentinelResource(
            value = "testFallBack",
            fallbackClass = CustomerFallBackHandler.class,
            fallback = "handlerNullPointerException",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handlerExceptionNoHotKey",
            exceptionsToIgnore = { NullPointerException.class }
    )
    public String testBlockFallBack(
    ){
        return "-------testBlockFallBack----------";
    }


    @GetMapping(value = "/get/payment/{id}")
    public String getPayment(@PathVariable("id")Integer id){
        return  paymentService.getId(id);
    }

}
