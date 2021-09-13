package com.springcloud.process;

import com.alibaba.fastjson.JSONObject;
import com.springcloud.entity.CanalStrategys;
import com.springcloud.entity.RabbitMQMessage;
import com.springcloud.enums.CanalClientEnums;

import java.util.Objects;

/**
 * @description: Canal处理
 * @author: Jiang
 * @create: 2021-09-10 10:51
 */
public class CanalDispose {

    private CanalStrategys canalStrategys;
    private CanalClientEnums canalClientEnums;
    private Object message;

    public CanalDispose(Object message, CanalClientEnums canalClientEnums, CanalStrategys canalStrategys) throws Exception {
        this.message = message;
        this.canalClientEnums = canalClientEnums;
        this.canalStrategys = canalStrategys;
        this.disposeMessage();
    }

    private void disposeMessage() throws Exception {
        if(Objects.isNull( this.canalClientEnums))
            throw new NullPointerException("连接模式为,null");
        if (Objects.equals(CanalClientEnums.RabbitMQ, canalClientEnums)) {
            message = JSONObject.parseObject(String.valueOf(message), RabbitMQMessage.class);
            Class c = canalStrategys.getRabbitMqCanalStrategys().get(((RabbitMQMessage) message).getTable());
            executeStrategy(c,new Object[]{message}, new Class[]{ RabbitMQMessage.class});
        } else {
            return;// TODO Tcp、Kafka、RocketMQ需要自行创建对应实体类转换
        }
    }

    private void executeStrategy(Class c, Object[] message, Class<?>... parameterTypes) throws Exception {
        c.getMethod("doOperation", parameterTypes).invoke(c.newInstance(), message);
    }
}