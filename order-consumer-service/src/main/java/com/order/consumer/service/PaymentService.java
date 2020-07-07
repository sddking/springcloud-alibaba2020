package com.order.consumer.service;


import com.order.consumer.service.fallback.PaymentFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "payment-service",fallback = PaymentFallbackImpl.class)
public interface PaymentService {

    @GetMapping(value = "/payment/get/{id}")
    public String getId(@PathVariable("id")Integer id);
}
