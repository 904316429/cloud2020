package com.springcloud.enums;

import java.util.HashMap;
import java.util.Map;

public enum CanalDisposeStrategyEnums {

    用户同步策略("USER_STRATEGY", "用户同步策略"),
    默认策略("defualt_strategy", "默认策略");

    public static String getInfoByCode(String code) {
        CanalDisposeStrategyEnums status = codeMap.get(code);
        if (status != null) {
            return status.getInfo();
        }
        return "未知状态";
    }

    public static String getCodeByInfo(String info) {
        CanalDisposeStrategyEnums status = infoMap.get(info);
        if (status != null) {
            return status.getCode();
        }
        return "F";
    }

    private static Map<String, CanalDisposeStrategyEnums> codeMap;

    private static Map<String, CanalDisposeStrategyEnums> infoMap;
    static {
        codeMap = new HashMap<String, CanalDisposeStrategyEnums>();
        infoMap = new HashMap<String, CanalDisposeStrategyEnums>();
        for (CanalDisposeStrategyEnums item : CanalDisposeStrategyEnums.values()) {
            codeMap.put(item.getCode(), item);
            infoMap.put(item.getInfo(), item);
        }
    }

    private final String code;
    private final String info;

    CanalDisposeStrategyEnums(String code, String info) {
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
