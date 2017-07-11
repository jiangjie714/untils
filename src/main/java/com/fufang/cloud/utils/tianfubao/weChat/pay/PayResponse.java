package com.fufang.cloud.utils.tianfubao.weChat.pay;

import com.fufang.cloud.utils.tianfubao.Response;

/**
 */
public class PayResponse extends Response{
    private String spid;//商户号
    private String listid;//天付宝单号
    private String sp_billno;//商户订单号
    private String pay_type;//支付类型
//    private String qrcode;//二维码信息
    private String merch_listid;//平台商户单号
    private String tran_amt;//交易金额
    private String cur_type;//币种类型
    private String sysd_time;//系统交易时间
    private String pay_params;//唤起微信支付的参数
    private String sub_openid;//openId
    
    public String getSub_openid() {
        return sub_openid;
    }
    
    public void setSub_openid(String sub_openid) {
        this.sub_openid = sub_openid;
    }
    
    public String getMerch_listid() {
        return merch_listid;
    }
    
    public void setMerch_listid(String merch_listid) {
        this.merch_listid = merch_listid;
    }
    
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
//
//    public String getQrcode() {
//        return qrcode;
//    }
//
//    public void setQrcode(String qrcode) {
//        this.qrcode = qrcode;
//    }

    //    public String getMerch_listid() {
//        return merch_listid;
//    }
//
//    public void setMerch_listid(String merch_listid) {
//        this.merch_listid = merch_listid;
//    }
//
    
    
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
    
    public String getPay_params() {
        return pay_params;
    }
    
    public void setPay_params(String pay_params) {
        this.pay_params = pay_params;
    }
    
    @Override
    public String toString() {
        return "PayResponse{" +
                "spid='" + spid + '\'' +
                ", listid='" + listid + '\'' +
                ", sp_billno='" + sp_billno + '\'' +
                ", pay_type='" + pay_type + '\'' +
                ", merch_listid='" + merch_listid + '\'' +
                ", tran_amt='" + tran_amt + '\'' +
                ", cur_type='" + cur_type + '\'' +
                ", sysd_time='" + sysd_time + '\'' +
                ", pay_params='" + pay_params + '\'' +
                ", sub_openid='" + sub_openid + '\'' +
                '}';
    }
}
