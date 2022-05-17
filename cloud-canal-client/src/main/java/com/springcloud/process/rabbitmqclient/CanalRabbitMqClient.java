package com.springcloud.process.rabbitmqclient;

import com.springcloud.entity.CanalStrategys;
import com.springcloud.process.CanalDispose;
import com.springcloud.enums.CanalClientEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * @description: 监听 RabbitMq Canal
 * @author: Jiang
 * @create: 2021-09-09 12:08
 */
@Slf4j
    @Component
    public class CanalRabbitMqClient {

        @Autowired
        private CanalStrategys canalStrategys;

        @RabbitListener(bindings = @QueueBinding(
                value = @Queue(value = "canal_queue", durable = "true"),
                exchange = @Exchange(name = "canal_exchange",
                        durable = "true",
                        type = "direct",
                        ignoreDeclarationExceptions = "true"),
                key = "canal_routingkey"
        )
        )
        @RabbitHandler
        public void onMessage(Message<String> message) throws Exception {
            try{
                new CanalDispose(message.getPayload(), CanalClientEnums.RabbitMQ, canalStrategys);
            } catch (Exception e){
                log.error("消息处理失败：" + message.getPayload() + " 原因：" + e.getMessage());
                throw e;
            }
        }

}