package com.fufang.cloud.utils.tianfubao.aliPay.paySingleQuery;

import com.fufang.cloud.utils.tianfubao.PaySingleQueryRecord;
import com.fufang.cloud.utils.tianfubao.Response;
import com.fufang.cloud.utils.wechat.Converters;

/**
 */
public class PaySingleQueryResponse extends Response{
    private String spid;
    private String data;
    
    private PaySingleQueryRecord record;
    
    public void parseData() {
        record = Converters.xml2Obj(data, PaySingleQueryRecord.class);
    }
    
    public PaySingleQueryRecord getRecord() {
        return record;
    }
    
    public void setRecord(PaySingleQueryRecord record) {
        this.record = record;
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
    
    @Override
    public String toString() {
        return "PaySingleQueryResponse{" +
                "spid='" + spid + '\'' +
                ", data='" + data + '\'' +
                ", record=" + record +
                '}';
    }
    
    public static void main(String[] args) {
        PaySingleQueryResponse paySingleQueryResponse = new PaySingleQueryResponse();
        paySingleQueryResponse.setData("<?xml version=\"1.0\" encoding=\"gb2312\" ?><record>" +
                "<listid>8120160324001</listid>" +
                "<state>33</state>" +
                "</record>");
        paySingleQueryResponse.parseData();
        System.out.println(paySingleQueryResponse.record);
    }
}
