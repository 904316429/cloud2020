package com.springcloud.controller;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-07-10 21:06
 */
@RestController
@Slf4j
public class PaymentController {


//    public static final String PAYMENT_URL = "http://localhost:8001"; // 单击版本
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVER"; // 集群版本

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id")Long id){

        return restTemplate.getForObject( PAYMENT_URL+"/payment/get/"+id, CommonResult.class);
    }

}
