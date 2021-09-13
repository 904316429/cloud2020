package com.springcloud.strategy.redisStrategy;

import com.springcloud.annotation.CanalStrategy;
import com.springcloud.entity.RabbitMQMessage;
import com.springcloud.enums.CanalClientEnums;
import com.springcloud.strategy.DisposeStrategy;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-09-10 10:17
 */
@Slf4j
@CanalStrategy(canalClientEnum = CanalClientEnums.RabbitMQ, database = "my_proj_db",table = "sys_user")
public class SysUserStrategy implements DisposeStrategy<RabbitMQMessage> {

    @Override
    public void doOperation(RabbitMQMessage messageJson) {
        log.info("消息处理：------ SysUserStrategy: " + messageJson );
//        RabbitMQMessage commonRabbitMQMessage = JSONObject.parseObject(messageJson, RabbitMQMessage.class);
//        System.out.println(commonRabbitMQMessage.toString());
    }
}