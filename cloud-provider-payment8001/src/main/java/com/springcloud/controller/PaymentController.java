package com.springcloud.controller;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import com.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-07-10 19:59
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        System.out.println(payment);
        int result = paymentService.create(payment);
        log.info("***** 插入结果："+result);
        if(result > 0)
            return new CommonResult(200, "插入成功:"+serverPort, result);
        else
            return new CommonResult(444, "插入失败");
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getById(@PathVariable("id") Long id){

        Payment payment = paymentService.getById(id);
        log.info("***** 查询结果："+payment);
        if(Objects.isNull(payment))
            return new CommonResult(444, "没有对应的记录");
        else
            return new CommonResult(200, "查询成功:"+serverPort, payment);
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();

        for (String e:services) {
            log.info("********** element:" + e);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVER");
        for (ServiceInstance instance: instances) {
            log.info( instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipKin(){
        return "hi, i'am paymentzipkin server fall back, welcome to springcloud, (*^▽^*)";
    }

}
