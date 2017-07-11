package com.fufang.cloud.utils.tianfubao.enums;

/**
 */
public enum AppTypeEnum {
    WECHAT(1, "微信"), ALIPAY(2, "支付宝");
    
    private int type;
    private String desc;
    
    AppTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
    
    public int getType() {
        return type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public static String getDesc(int type) {
        for (AppTypeEnum appTypeEnum : values()) {
            if (appTypeEnum.type == type) {
                return appTypeEnum.desc;
            }
        }
        return "";
    }
}
