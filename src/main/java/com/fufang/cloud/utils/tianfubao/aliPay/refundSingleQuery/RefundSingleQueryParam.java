package com.fufang.cloud.utils.tianfubao.aliPay.refundSingleQuery;

import com.fufang.cloud.utils.tianfubao.Param;

/**
 */
public class RefundSingleQueryParam extends Param{
    private String spid;
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
