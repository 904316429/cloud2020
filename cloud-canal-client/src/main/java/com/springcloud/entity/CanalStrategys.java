package com.springcloud.entity;

import java.util.Map;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-09-10 14:45
 */
public class CanalStrategys {

    private Map<String, Class> rabbitMqCanalStrategys;
    private Map<String, Class> rocketMqCanalStrategys;
    private Map<String, Class> kafkaCanalStrategys;
    private Map<String, Class> tcpCanalStrategys;

    public Map<String, Class> getRabbitMqCanalStrategys() {
        return rabbitMqCanalStrategys;
    }

    public void setRabbitMqCanalStrategys(Map<String, Class> rabbitMqCanalStrategys) {
        this.rabbitMqCanalStrategys = rabbitMqCanalStrategys;
    }

    public Map<String, Class> getRocketMqCanalStrategys() {
        return rocketMqCanalStrategys;
    }

    public void setRocketMqCanalStrategys(Map<String, Class> rocketMqCanalStrategys) {
        this.rocketMqCanalStrategys = rocketMqCanalStrategys;
    }

    public Map<String, Class> getKafkaCanalStrategys() {
        return kafkaCanalStrategys;
    }

    public void setKafkaCanalStrategys(Map<String, Class> kafkaCanalStrategys) {
        this.kafkaCanalStrategys = kafkaCanalStrategys;
    }

    public Map<String, Class> getTcpCanalStrategys() {
        return tcpCanalStrategys;
    }

    public void setTcpCanalStrategys(Map<String, Class> tcpCanalStrategys) {
        this.tcpCanalStrategys = tcpCanalStrategys;
    }

    @Override
    public String toString() {
        return "CanalStrategys{" +
                "rabbitMqCanalStrategys=" + rabbitMqCanalStrategys +
                ", rocketMqCanalStrategys=" + rocketMqCanalStrategys +
                ", kafkaCanalStrategys=" + kafkaCanalStrategys +
                ", tcpCanalStrategys=" + tcpCanalStrategys +
                '}';
    }
}
