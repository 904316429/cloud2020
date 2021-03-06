package com.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: Jiang
 * @create: 2021-07-18 15:11
 */
@Configuration
public class MySelfRule {

    /**
     * Ribbon 负载算法有7种
     * 1.new RoundRobinRule 轮询(默认)
     * 2.new RandomRule 随机
     * 3.new RetryRule 先按照RoundRobinRule的策略获取服务, 如果获取服务失败则在指定时间内会进行重试, 获取可用的服务
     * 4.new WeightedResponseTimeRule 对RoundRobinRule的扩展, 响应速度越快的实例选择权重越大, 越容易被选择
     * 5.new BestAvailableRule 会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务, 然后选择一个并发量最小的服务
     * 6.new AvailabilityFilteringRule 先过滤掉故障实例, 在选择并发较小的实例
     * 7.new ZoneAvoidanceRule 默认规则, 复合判断server所在区域的性能和server的可用性选择服务器
     * @return
     */
    @Bean
    public IRule myRule(){
        return new RandomRule();// 定义为随机
    }

}
