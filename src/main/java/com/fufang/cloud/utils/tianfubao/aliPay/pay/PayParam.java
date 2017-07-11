package com.fufang.cloud.utils.tianfubao.aliPay.pay;

import com.fufang.cloud.utils.tianfubao.Param;

/**
 */
public class PayParam extends Param{
    private String spid;//商户号
    private String notify_url;//通知回调URL
    private String sp_billno;//商户订单号
    private String pay_type;//支付类型
    private int tran_amt;//交易金额
    private String auth_code;//二维码
    private String item_name;//商品描述
    private String item_attach;//商品附加数据
    
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
    
    public String getPay_type() {
        return pay_type;
    }
    
    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }
    
    public int getTran_amt() {
        return tran_amt;
    }
    
    public void setTran_amt(int tran_amt) {
        this.tran_amt = tran_amt;
    }
    
    public String getAuth_code() {
        return auth_code;
    }
    
    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }
    
    public String getItem_name() {
        return item_name;
    }
    
    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
    
    public String getItem_attach() {
        return item_attach;
    }
    
    public void setItem_attach(String item_attach) {
        this.item_attach = item_attach;
    }
}
