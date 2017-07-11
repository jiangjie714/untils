package com.fufang.cloud.utils.tianfubao;

/**
 */
public class Response {
    private String sign;
    private String retcode;
    private String retmsg;
    
    private boolean success = false;//同步调用成功,可轮询支付结果
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getSign() {
        return sign;
    }
    
    public void setSign(String sign) {
        this.sign = sign;
    }
    
    public String getRetcode() {
        return retcode;
    }
    
    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }
    
    public String getRetmsg() {
        return retmsg;
    }
    
    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }
}
