package com.fufang.cloud.utils.tianfubao.aliPay.refund;

import com.fufang.cloud.utils.tianfubao.Response;

/**
 */
public class RefundResponse extends Response {
    private String spid;
    private String refund_listid;//天付宝退款单号
    private String sp_billno;//原商户订单号
    private String merch_listid;
    private String listid;
    private String tran_amt;
    private String tran_time;
    private String sysd_time;
    
    public String getListid() {
        return listid;
    }
    
    public void setListid(String listid) {
        this.listid = listid;
    }
    
    public String getSpid() {
        return spid;
    }
    
    public void setSpid(String spid) {
        this.spid = spid;
    }
    
    public String getRefund_listid() {
        return refund_listid;
    }
    
    public void setRefund_listid(String refund_listid) {
        this.refund_listid = refund_listid;
    }
    
    public String getSp_billno() {
        return sp_billno;
    }
    
    public void setSp_billno(String sp_billno) {
        this.sp_billno = sp_billno;
    }

    public String getMerch_listid() {
        return merch_listid;
    }

    public void setMerch_listid(String merch_listid) {
        this.merch_listid = merch_listid;
    }
    
    public String getTran_amt() {
        return tran_amt;
    }
    
    public void setTran_amt(String tran_amt) {
        this.tran_amt = tran_amt;
    }
    
    public String getTran_time() {
        return tran_time;
    }
    
    public void setTran_time(String tran_time) {
        this.tran_time = tran_time;
    }
    
    public String getSysd_time() {
        return sysd_time;
    }
    
    public void setSysd_time(String sysd_time) {
        this.sysd_time = sysd_time;
    }
    
    @Override
    public String toString() {
        return "RefundResponse{" +
                "spid='" + spid + '\'' +
                ", refund_listid='" + refund_listid + '\'' +
                ", sp_billno='" + sp_billno + '\'' +
                ", merch_listid='" + merch_listid + '\'' +
                ", listid='" + listid + '\'' +
                ", tran_amt='" + tran_amt + '\'' +
                ", tran_time='" + tran_time + '\'' +
                ", sysd_time='" + sysd_time + '\'' +
                '}';
    }
}
