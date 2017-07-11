package com.fufang.cloud.utils.tianfubao.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询天付宝订单的状态
 */
public enum TranStateEnum {
    NEW("1", "新建"), PROCESSING("2", "交易处理中"), SUCCESS("3", "交易成功"), FAIL("4", "交易失败"), CLOSE("5", "关闭");
    
    private String status;
    private String describe;
    
    TranStateEnum(String status, String describe) {
        this.status = status;
        this.describe = describe;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getDescribe() {
        return describe;
    }
    
    public static Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>();
        for (TranStateEnum activityStatusEnum : values()) {
            map.put(activityStatusEnum.status, activityStatusEnum.describe);
        }
        return map;
    }
    
    public static String getStatusDescribe(String status) {
        for (TranStateEnum activityStatusEnum : values()) {
            if (activityStatusEnum.status.equals(status)) {
                return activityStatusEnum.describe;
            }
        }
        return "";
    }
}
