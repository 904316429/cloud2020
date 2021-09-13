package com.springcloud.config.filter;

import com.springcloud.CanalMain;
import com.springcloud.enums.CanalClientEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.util.Objects;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-09-10 19:05
 */
@Slf4j
public class CustomFilter implements TypeFilter {

    private static final String LISTENER_PACKAGE = ".rabbitmqclient.";
    private static final String ROCKETMQ_PACKAGE = ".rocketmqclient.";
    private static final String KAFKA_PACKAGE = ".kafkaclient.";
    private static final String TCP_PACKAGE = ".tcpclient.";

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) {

        // 通过MetadataReader获得各种信息，然后根据自己的需求返回boolean，实例表示包名含有aaa路径的类名将满足筛选条件
        if(!Objects.equals(CanalClientEnums.RabbitMQ.getCode(), CanalMain.canalClientType))
            return metadataReader.getClassMetadata().getClassName().contains(LISTENER_PACKAGE);
        if(!Objects.equals(CanalClientEnums.RocketMQ.getCode(), CanalMain.canalClientType))
            return metadataReader.getClassMetadata().getClassName().contains(ROCKETMQ_PACKAGE);
        if(!Objects.equals(CanalClientEnums.Kafka.getCode(), CanalMain.canalClientType))
            return metadataReader.getClassMetadata().getClassName().contains(KAFKA_PACKAGE);
        if(!Objects.equals(CanalClientEnums.Tcp.getCode(), CanalMain.canalClientType))
            return metadataReader.getClassMetadata().getClassName().contains(TCP_PACKAGE);
        return false;
    }

}
