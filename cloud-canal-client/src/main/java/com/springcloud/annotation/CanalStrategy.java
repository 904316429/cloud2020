package com.springcloud.annotation;

import com.springcloud.enums.CanalClientEnums;

import java.lang.annotation.*;


/**
 * @description: 策略注解
 * @author: Jiang
 * @create: 2021-09-10 14:33
 */
@Target(value = {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CanalStrategy {

    CanalClientEnums canalClientEnum() default CanalClientEnums.RabbitMQ;
    // 数据库名称
    String database() default "";
    // 表名称
    String table() default "";
}
