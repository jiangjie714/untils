package com.fufang.cloud.utils.tianfubao.weChat.pay;

import com.fufang.cloud.utils.tianfubao.Param;

/**
 */
public class PayParam extends Param{
    private String spid;//商户号
    private String notify_url;//通知回调URL
    private String sp_billno;//商户订单号
    private String pay_type;//支付类型
    private int tran_amt;//交易金额
    private String Openid;//
    private String auth_code;//用户二维码内容,微信刷卡必填
    private String item_name;//商品描述
    private String bank_mch_name;//三级商户名称
    private Integer bank_mch_id;//三级商户ID
    
    public String getOpenid() {
        return Openid;
    }
    
    public void setOpenid(String openid) {
        Openid = openid;
    }
    
    public String getPay_type() {
        return pay_type;
    }
    
    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }
    
    public String getAuth_code() {
        return auth_code;
    }
    
    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }
    
    public String getSpid() {
        return spid;
    }
    
    public void setSpid(String spid) {
        this.spid = spid;
    }
    
    public String getNotify_url() {
        return notify_url;
    }
    
    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
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
    
    public String getItem_name() {
        return item_name;
    }
    
    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
    
    public String getBank_mch_name() {
        return bank_mch_name;
    }
    
    public void setBank_mch_name(String bank_mch_name) {
        this.bank_mch_name = bank_mch_name;
    }
    
    public Integer getBank_mch_id() {
        return bank_mch_id;
    }
    
    public void setBank_mch_id(Integer bank_mch_id) {
        this.bank_mch_id = bank_mch_id;
    }
}
