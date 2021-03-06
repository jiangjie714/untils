package com.fufang.cloud.utils.tianfubao.enums;

/**
 */
public enum  WeChatPayTypeEnum {
    SWIPINGCARD("800208", "刷卡支付"), PUBLICACCOUNT("800207", "公众号支付");
    
    private String type;
    private String desc;
    
    WeChatPayTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public static String getDescribe(String type) {
        for (WeChatPayTypeEnum typeEnum : values()) {
            if (typeEnum.type.equals(type)) {
                return typeEnum.desc;
            }
        }
        return "";
    }
}
