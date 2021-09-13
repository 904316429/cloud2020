package com.springcloud;

import com.springcloud.config.filter.CustomFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-09-07 15:14
 */
@Slf4j
@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {CustomFilter.class}))
public class CanalMain {

    // 用于配置 CanalServer是使用什么模式进行推送, 本来想配置到 yml中的, 但是扫描包时 @Value 获取不到值, 所以定义到此处
    public final static String canalClientType = "rabbitMQ";

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(CanalMain.class, args);
    }

}
