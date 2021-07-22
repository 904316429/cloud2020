package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-07-22 23:44
 */
@SpringBootApplication
@EnableEurekaClient
public class ConfigCenterMain3366 {

    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3366.class, args);
    }
}
