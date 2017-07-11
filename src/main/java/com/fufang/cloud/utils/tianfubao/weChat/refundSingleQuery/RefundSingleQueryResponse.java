package com.fufang.cloud.utils.tianfubao.weChat.refundSingleQuery;

import com.fufang.cloud.utils.tianfubao.Response;
import com.fufang.cloud.utils.wechat.Converters;

/**
 */
public class RefundSingleQueryResponse extends Response {
    private String spid;
    private String data;
    
    public Record parseData() {
        return Converters.xml2Obj(data, Record.class);
    }
    
    public String getSpid() {
        return spid;
    }
    
    public void setSpid(String spid) {
        this.spid = spid;
    }
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
}
