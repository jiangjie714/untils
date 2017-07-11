package com.fufang.cloud.utils.tianfubao.weChat.paySingleQuery;

import com.fufang.cloud.utils.tianfubao.Param;

/**
 */
public class PaySingleQueryParam extends Param{
    private String spid;
    private String sp_billno;
    
    public PaySingleQueryParam(String spid, String sp_billno) {
        this.spid = spid;
        this.sp_billno = sp_billno;
    }
    
    public PaySingleQueryParam() {
    }
    
    public String getSpid() {
        return spid;
    }
    
    public void setSpid(String spid) {
        this.spid = spid;
    }
    
    public String getSp_billno() {
        return sp_billno;
    }
    
    public void setSp_billno(String sp_billno) {
        this.sp_billno = sp_billno;
    }
}
