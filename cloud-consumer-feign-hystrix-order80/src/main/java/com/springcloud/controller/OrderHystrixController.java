package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-07-20 21:06
 */
@RestController
@Slf4j
//@DefaultProperties( defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_Ok(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_Ok(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
//    @HystrixCommand
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
//        int i = 10 / 0;
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        return result;
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池：  "+ Thread.currentThread().getName()
                +"我是消费者80，对方支付系统繁忙请10秒后再试 (灬ꈍ ꈍ灬)";
    }

    // 下面是全局的 发了fallback方法
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息, 请稍候再试 ┭┮﹏┭┮";
    }

}
