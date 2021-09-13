package com.springcloud.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: Canal默认处理策略
 * @author: Jiang
 * @create: 2021-09-10 10:14
 */
@Slf4j
public class CanalDefaultStrategy implements DisposeStrategy {

    @Override
    public void doOperation(Object messageJson) {
       log.info("默认消息处理： ===" + messageJson.toString());
    }
}
