package com.fufang.cloud.utils.tianfubao.weChat.refund;

import com.fufang.cloud.utils.tianfubao.Param;

/**
 */
public class RefundParam extends Param{
    private String spid;
    private String sp_billno;
    private String sp_refundid;
    private int tran_amt;
    
    public RefundParam(String spid, String sp_billno, String sp_refundid, int tran_amt) {
        this.spid = spid;
        this.sp_billno = sp_billno;
        this.sp_refundid = sp_refundid;
        this.tran_amt = tran_amt;
    }
    
    public RefundParam() {
    }
    
    public String getSp_refundid() {
        return sp_refundid;
    }
    
    public void setSp_refundid(String sp_refundid) {
        this.sp_refundid = sp_refundid;
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
    
    public int getTran_amt() {
        return tran_amt;
    }
    
    public void setTran_amt(int tran_amt) {
        this.tran_amt = tran_amt;
    }
    
    @Override
    public String toString() {
        return "RefundParam{" +
                "spid='" + spid + '\'' +
                ", sp_billno='" + sp_billno + '\'' +
                ", sp_refundid='" + sp_refundid + '\'' +
                ", tran_amt=" + tran_amt +
                '}';
    }
}
