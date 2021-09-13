package com.springcloud.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 初始化 canalServer需要的 Exchange、Queue
 * @description:
 * @author: Jiang
 * @create: 2021-09-09 17:25
 */
@Configuration
public class RabbitMqConfig {

    //队列 起名：canal_queue
    @Bean
    public Queue TestDirectQueue() {
        return new Queue("canal_queue",true);
    }

    //Direct交换机 起名：canal_exchange
    @Bean
    DirectExchange TestDirectExchange() {
        return new DirectExchange("canal_exchange");
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：canal_routingkey
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("canal_routingkey");
    }
}
