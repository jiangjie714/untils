package com.fufang.cloud.utils.tianfubao.weChat.close;

import com.fufang.cloud.utils.tianfubao.Param;

/**
 */
public class CloseParam extends Param{
    private String spid;
    private String sp_billno;
    private int tran_amt;
    
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
    
    public int getTran_amt() {
        return tran_amt;
    }
    
    public void setTran_amt(int tran_amt) {
        this.tran_amt = tran_amt;
    }
}
