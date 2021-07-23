package com.springcloud.service.impl;

import cn.hutool.core.lang.UUID;
import com.springcloud.service.IMessageProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-07-23 12:24
 */
@EnableBinding(Source.class)// 定义消息的推送管道 源
@Slf4j
public class IMessageProviderServiceImpl implements IMessageProviderService {

    @Resource
    private MessageChannel output;// 消息发送管道

    @Override
    public String send() {
        String  serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("*******serial:"+serial);
        return null;
    }
}
