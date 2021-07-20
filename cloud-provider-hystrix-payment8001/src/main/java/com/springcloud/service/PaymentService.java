package com.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-07-20 20:36
 */
@Service
public class PaymentService {

    public String paymentInfo_Ok(Integer id){
        return "线程池：  "+ Thread.currentThread().getName()
                +"   paymentInfo_Od,id: "+id+ "\t o(*￣︶￣*)o";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_Timeout(Integer id){
        int timeNumber = 3;
//        int i = 10 / 0;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：  "+ Thread.currentThread().getName()
                +"   paymentInfo_Od,id: "+id+ "\t o(*￣︶￣*)o" + "  耗时(秒):"+ timeNumber;
    }

    public String paymentInfo_TimeoutHandler(Integer id){
        return "线程池：  "+ Thread.currentThread().getName()
                +"   8001系统繁忙或运行报错,请稍候再试,id: "+id+ "\t (灬ꈍ ꈍ灬)";
    }

    /// ******** 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),// 时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){
        if(id < 0){
            throw new RuntimeException("****** id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t 调用成功,流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能负数,请稍候在尝试o(╥﹏╥)o id:" + id;
    }
}
