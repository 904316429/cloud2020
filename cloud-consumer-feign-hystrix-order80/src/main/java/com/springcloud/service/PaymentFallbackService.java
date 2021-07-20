package com.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-07-20 22:04
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {

    @Override
    public String paymentInfo_Ok(Integer id) {
        return "==========PaymentFallbackService fall back-paymentInfo_Ok, o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "==========PaymentFallbackService fall back-paymentInfo_Timeout, o(╥﹏╥)o";
    }
}
