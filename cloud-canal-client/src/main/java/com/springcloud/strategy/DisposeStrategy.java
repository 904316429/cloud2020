package com.springcloud.strategy;

/**
 * @description: 消息实体类 RabbitMq
 * @author: Jiang
 * @create: 2021-09-10 10:09:27
 */
public interface DisposeStrategy<T> {

    // 处理方式
    void doOperation(T messageJson);

}
