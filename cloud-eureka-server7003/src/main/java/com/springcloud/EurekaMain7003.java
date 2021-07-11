package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-07-11 16:26
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaMain7003 {

    public static void main(String[] args) {
        SpringApplication.run( EurekaMain7003.class,args);
    }

}
