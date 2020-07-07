package com.order.consumer.service.fallback;

import com.order.consumer.service.PaymentService;
import org.springframework.stereotype.Component;

/**
 * @author 180465
 * @date 2020/7/7 9:40
 */
@Component
public class PaymentFallbackImpl implements PaymentService {
    public String getId(Integer id) {
        return " fall back exception！";
    }
}
