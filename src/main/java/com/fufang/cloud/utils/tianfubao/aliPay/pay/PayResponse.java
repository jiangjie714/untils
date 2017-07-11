package com.fufang.cloud.utils.tianfubao.aliPay.pay;

import com.fufang.cloud.utils.tianfubao.Response;

/**
 */
public class PayResponse extends Response{
    private String spid;
    private String listid;
    private String sp_billno;
    private String pay_type;
    private String qrcode;
    private String merch_listid;
    private String tran_amt;
    private String cur_type;
    private String sysd_time;
    
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
    
    public String getPay_type() {
        return pay_type;
    }
    
    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }
    
    public String getQrcode() {
        return qrcode;
    }
    
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
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
    
    public String getCur_type() {
        return cur_type;
    }
    
    public void setCur_type(String cur_type) {
        this.cur_type = cur_type;
    }
    
    public String getSysd_time() {
        return sysd_time;
    }
    
    public void setSysd_time(String sysd_time) {
        this.sysd_time = sysd_time;
    }
    
    @Override
    public String toString() {
        return "PayResponse{" +
                "spid='" + spid + '\'' +
                ", listid='" + listid + '\'' +
                ", sp_billno='" + sp_billno + '\'' +
                ", pay_type='" + pay_type + '\'' +
                ", qrcode='" + qrcode + '\'' +
                ", merch_listid='" + merch_listid + '\'' +
                ", tran_amt='" + tran_amt + '\'' +
                ", cur_type='" + cur_type + '\'' +
                ", sysd_time='" + sysd_time + '\'' +
                '}';
    }
}
