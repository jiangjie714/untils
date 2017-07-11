package com.fufang.cloud.utils.tianfubao.weChat.refundSingleQuery;

import com.fufang.cloud.utils.tianfubao.Param;

/**
 */
public class RefundSingleQueryParam extends Param{
    private String spid;
    //todo 商户退款单号哪来的 notify_id是什么东西 同步返回的retcode==0代表什么
    private String refund_listid;
    
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
}
