package com.springcloud.controller;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import com.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

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
    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id")Long id){

        return restTemplate.getForObject( PAYMENT_URL+"/payment/get/"+id, CommonResult.class);
    }

    @GetMapping("/consumer/payment2/create")
    public CommonResult<Payment> create2(Payment payment) {

        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);

        if (entity.getStatusCode().is2xxSuccessful())
            return entity.getBody();
        else
            return new CommonResult<>(444, "插入失败");
    }

    @GetMapping("/consumer/payment2/get/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id")Long id) {

        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id, CommonResult.class);
//        return restTemplate.getForObject( PAYMENT_URL+"/payment/get/"+id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful())
            return entity.getBody();
        else
            return new CommonResult<>(444, "操作失败！");
    }

    @GetMapping(value = "consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVER");
        if (instances == null || instances.size() <= 0)
            return null;

        ServiceInstance serviceInstance = loadBalancer.instances(instances);

        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb", String.class);
    }

    // ********** zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipKin(){
        String result = restTemplate.getForObject("http://localhost:8001"+
                "/payment/zipkin/", String.class);
        return result;
    }
}
