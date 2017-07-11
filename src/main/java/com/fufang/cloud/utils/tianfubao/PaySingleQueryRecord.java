package com.fufang.cloud.utils.tianfubao;

/**
 */
public class PaySingleQueryRecord {
    private String listid;
    private String sp_billno;
//    private String merch_listid;
    private String spid;
    private String pay_type;
    private String tran_amt;
    private String state;
    private String item_name;//商品名称
    private String item_attach;//商品附加信息
    private String create_time;
    private String close_time;
    private String pay_time;
    private String sub_openid;
    
    public String getSub_openid() {
        return sub_openid;
    }
    
    public void setSub_openid(String sub_openid) {
        this.sub_openid = sub_openid;
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

    public String getSpid() {
        return spid;
    }
    
    public void setSpid(String spid) {
        this.spid = spid;
    }
    
    public String getPay_type() {
        return pay_type;
    }
    
    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }
    
    public String getTran_amt() {
        return tran_amt;
    }
    
    public void setTran_amt(String tran_amt) {
        this.tran_amt = tran_amt;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
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
    
    public String getCreate_time() {
        return create_time;
    }
    
    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
    
    public String getClose_time() {
        return close_time;
    }
    
    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }
    
    public String getPay_time() {
        return pay_time;
    }
    
    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }
    
    @Override
    public String toString() {
        return "PaySingleQueryRecord{" +
                "listid='" + listid + '\'' +
                ", sp_billno='" + sp_billno + '\'' +
                ", spid='" + spid + '\'' +
                ", pay_type='" + pay_type + '\'' +
                ", tran_amt='" + tran_amt + '\'' +
                ", state='" + state + '\'' +
                ", item_name='" + item_name + '\'' +
                ", item_attach='" + item_attach + '\'' +
                ", create_time='" + create_time + '\'' +
                ", close_time='" + close_time + '\'' +
                ", pay_time='" + pay_time + '\'' +
                ", sub_openid='" + sub_openid + '\'' +
                '}';
    }
}
