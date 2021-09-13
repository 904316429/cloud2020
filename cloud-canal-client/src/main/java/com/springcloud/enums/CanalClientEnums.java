package com.springcloud.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: CanalClient类型
 * @author: Jiang
 * @create: 2021-09-10 10:51
 */
public enum CanalClientEnums {

    Tcp("tcp", "Tcp"),
    RabbitMQ("rabbitMQ", "RabbitMQ"),
    Kafka("kafka", "Kafka"),
    RocketMQ("rocketMQ", "RocketMQ"),
    ;

    public static String getInfoByCode(String code) {
        CanalClientEnums status = codeMap.get(code);
        if (status != null) {
            return status.getInfo();
        }
        return "未知状态";
    }

    public static String getCodeByInfo(String info) {
        CanalClientEnums status = infoMap.get(info);
        if (status != null) {
            return status.getCode();
        }
        return "F";
    }

    private static Map<String, CanalClientEnums> codeMap;

    private static Map<String, CanalClientEnums> infoMap;
    static {
        codeMap = new HashMap<String, CanalClientEnums>();
        infoMap = new HashMap<String, CanalClientEnums>();
        for (CanalClientEnums item : CanalClientEnums.values()) {
            codeMap.put(item.getCode(), item);
            infoMap.put(item.getInfo(), item);
        }
    }

    private final String code;
    private final String info;

    CanalClientEnums(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }


}
