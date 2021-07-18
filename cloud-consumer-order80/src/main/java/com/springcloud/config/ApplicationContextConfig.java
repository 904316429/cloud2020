package com.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-07-10 21:02
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
//    @LoadBalanced // 使用@LoadBalanced 注解赋予RestTemplate 负载均衡的能力, 默认的轮询算法
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
