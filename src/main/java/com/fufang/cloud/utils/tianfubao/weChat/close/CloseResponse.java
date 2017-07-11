package com.fufang.cloud.utils.tianfubao.weChat.close;

import com.fufang.cloud.utils.tianfubao.Response;

/**
 */
public class CloseResponse extends Response{
    private String spid;//商户号
    private String listid;//天付宝单号
    private String sp_billno;//商户订单号
    private String tran_time;//发起关闭时间
    private String tran_amt;//交易金额
    private String sysd_time;//系统处理时间
    
    public String getSpid() {
        return spid;
    }
    
    public void setSpid(String spid) {
        this.spid = spid;
    }
    
    public String getListid() {
        return listid;
    }
    
    public void setListid(String listid) {
        this.listid = listid;
    }
    
    public String getSp_billno() {
        return sp_billno;
    }
    
    public void setSp_billno(String sp_billno) {
        this.sp_billno = sp_billno;
    }
    
    public String getTran_time() {
        return tran_time;
    }
    
    public void setTran_time(String tran_time) {
        this.tran_time = tran_time;
    }
    
    public String getTran_amt() {
        return tran_amt;
    }
    
    public void setTran_amt(String tran_amt) {
        this.tran_amt = tran_amt;
    }
    
    public String getSysd_time() {
        return sysd_time;
    }
    
    public void setSysd_time(String sysd_time) {
        this.sysd_time = sysd_time;
    }
}
