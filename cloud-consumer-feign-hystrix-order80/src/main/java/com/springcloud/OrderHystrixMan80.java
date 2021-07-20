package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-07-20 21:03
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class OrderHystrixMan80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixMan80.class,args);
    }
}
