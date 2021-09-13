package com.springcloud.config;

import com.springcloud.CanalMain;
import com.springcloud.annotation.CanalStrategy;
import com.springcloud.entity.CanalStrategys;
import com.springcloud.enums.CanalClientEnums;
import com.springcloud.utils.ScanClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @description: 初始化 CanalStrategy
 * @author: Jiang
 * @create: 2021-09-10 14:33
 */
@Slf4j
@Configuration
public class InitCanalStrategy {

    @Bean
    public CanalStrategys init() throws Exception {
        log.info("初始化 Canal策略 Class -------- begin --  "+ LocalDateTime.now());
        CanalStrategys canalStrategys = this.initCanalStrategs("com.springcloud.strategy");
        log.info("初始化 Canal策略 Class -------- end --  "+ LocalDateTime.now());
        return canalStrategys;
    }

//    public static void main(String[] args) throws Exception {
//        Object message = "{\"data\":[{\"id\":\"5\",\"name\":\"Billie1\",\"age\":\"24\",\"email\":\"1111.@qq.com\"}],\"database\":\"school_db\",\"es\":1631267769000,\"id\":28,\"isDdl\":false,\"mysqlType\":{\"id\":\"bigint(20)\",\"name\":\"varchar(30)\",\"age\":\"int(11)\",\"email\":\"varchar(50)\"},\"old\":[{\"name\":\"Billie\"}],\"pkNames\":[\"id\"],\"sql\":\"\",\"sqlType\":{\"id\":-5,\"name\":12,\"age\":4,\"email\":12},\"table\":\"user\",\"ts\":1631267684994,\"type\":\"UPDATE\"}";
//        CanalStrategys canalStrategys = initCanalStrategs("com.springcloud.strategy");
//        message = JSONObject.parseObject(String.valueOf(message), RabbitMQMessage.class);
//        Class c = canalStrategys.getRabbitMqCanalStrategys().get(((RabbitMQMessage) message).getTable());
//        executeStrategy(c,new Object[]{message}, new Class[]{ RabbitMQMessage.class});
//    }
//
//    private static void executeStrategy(Class c, Object[] message, Class<?>... parameterTypes) throws Exception {
//        c.getMethod("doOperation", parameterTypes).invoke(c.newInstance(), message);
//    }

    private static CanalStrategys initCanalStrategs(String pack) throws Exception {
        // TODO 此处有优化点, 因为该服务只能正对某一种连接方式所依不需要全部加载, 但是因为启动时加载可以放后优化并不影响效率, 如果策略类太多导致使用内存过大可以考虑优化
        Map<String, Class> rabbitMqCanalStrategys = new HashMap<>();
        Map<String, Class> rocketMqCanalStrategys = new HashMap<>();
        Map<String, Class> kafkaCanalStrategys = new HashMap<>();
        Map<String, Class> tcpCanalStrategys = new HashMap<>();
        Set<Class<?>> s = ScanClassUtil.getClasses(pack);
        for (Class c : s) {
            if (Objects.isNull(c) || c.isInterface())
                continue;
            classifyClass(rabbitMqCanalStrategys, rocketMqCanalStrategys, kafkaCanalStrategys, tcpCanalStrategys, c);
        }
        CanalStrategys canalStrategys = new CanalStrategys();
        canalStrategys.setRabbitMqCanalStrategys(rabbitMqCanalStrategys);
        canalStrategys.setRocketMqCanalStrategys(rocketMqCanalStrategys);
        canalStrategys.setKafkaCanalStrategys(kafkaCanalStrategys);
        canalStrategys.setTcpCanalStrategys(tcpCanalStrategys);
        return canalStrategys;
    }

    private static void classifyClass(Map<String, Class> rabbitMqCanalStrategys, Map<String, Class> rocketMqCanalStrategys, Map<String, Class> kafkaCanalStrategys, Map<String, Class> tcpCanalStrategys,  Class c) throws Exception {
        Annotation annotation = c.newInstance().getClass().getAnnotation(CanalStrategy.class);
        if (Objects.isNull(annotation)) return;
        CanalClientEnums canalClientEnums = ((CanalStrategy) annotation).canalClientEnum();
        if (Objects.equals(CanalClientEnums.RabbitMQ, canalClientEnums)) {
            rabbitMqCanalStrategys.put(((CanalStrategy) annotation).table(), c);
        } else if (Objects.equals(CanalClientEnums.Kafka, canalClientEnums)) {
            kafkaCanalStrategys.put(((CanalStrategy) annotation).table(), c);
        } else if (Objects.equals(CanalClientEnums.RocketMQ, canalClientEnums)) {
            rocketMqCanalStrategys.put(((CanalStrategy) annotation).table(), c);
        } else if (Objects.equals(CanalClientEnums.Tcp, canalClientEnums)){
            tcpCanalStrategys.put(((CanalStrategy) annotation).table(), c);
        }
    }

}
