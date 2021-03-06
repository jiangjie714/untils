package com.fufang.cloud.utils.tianfubao;

import com.fufang.cloud.utils.tianfubao.aliPay.close.CloseParam;
import com.fufang.cloud.utils.tianfubao.aliPay.close.CloseResponse;
import com.fufang.cloud.utils.tianfubao.aliPay.pay.PayParam;
import com.fufang.cloud.utils.tianfubao.aliPay.pay.PayResponse;
import com.fufang.cloud.utils.tianfubao.aliPay.paySingleQuery.PaySingleQueryParam;
import com.fufang.cloud.utils.tianfubao.aliPay.paySingleQuery.PaySingleQueryResponse;
import com.fufang.cloud.utils.tianfubao.aliPay.refund.RefundParam;
import com.fufang.cloud.utils.tianfubao.aliPay.refund.RefundResponse;
import com.fufang.cloud.utils.tianfubao.aliPay.refundSingleQuery.RefundSingleQueryParam;
import com.fufang.cloud.utils.tianfubao.aliPay.refundSingleQuery.RefundSingleQueryResponse;

import javax.servlet.http.HttpServletRequest;

/**
 */
public class AliPayUtil extends PaySupport {
    // 正式地址 todo
    private static final String PAY_URL = "http://upay.tfb8.com/cgi-bin/v2.0/api_ali_pay_apply.cgi";
    private static final String CLOSE_URL = "http://upay.tfb8.com/cgi-bin/v2.0/api_ali_pay_cancel.cgi";
    private static final String PAY_SINGLE_QUERY = "http://upay.tfb8.com/cgi-bin/v2.0/api_ali_pay_qry.cgi";
    private static final String REFUND_SINGLE_QUERY = "http://upay.tfb8.com/cgi-bin/v2.0/api_ali_refund_qry.cgi";
    private static final String REFUND_URL = "http://upay.tfb8.com/cgi-bin/v2.0/api_ali_refund.cgi";
    
//    private static final String PAY_URL = "http://api.gcdev.tfb8.com/cgi-bin/v2.0/api_ali_pay_apply.cgi";
//    private static final String CLOSE_URL = "http://api.gcdev.tfb8.com/cgi-bin/v2.0/api_ali_pay_cancel.cgi";
//    private static final String PAY_SINGLE_QUERY = "http://api.gcdev.tfb8.com/cgi-bin/v2.0/api_ali_pay_qry.cgi";
//    private static final String REFUND_SINGLE_QUERY = "http://api.gcdev.tfb8.com/cgi-bin/v2.0/api_ali_refund_qry.cgi";
//    private static final String REFUND_URL = "http://api.gcdev.tfb8.com/cgi-bin/v2.0/api_ali_refund.cgi";
    
    public static PayResponse pay(PayParam payParam, String secret, HttpServletRequest request) throws Exception {
        return pay(PAY_URL, payParam, secret, request, PayResponse.class);
    }
    
    public static CloseResponse close(CloseParam closeParam, String secret) throws Exception {
        return close(CLOSE_URL, closeParam, secret, CloseResponse.class);
    }
    
    public static PaySingleQueryResponse paySingQuery(PaySingleQueryParam paySingleQueryParam, String secret) throws Exception {
        PaySingleQueryResponse response = paySingQuery(PAY_SINGLE_QUERY, paySingleQueryParam, secret, PaySingleQueryResponse.class);
        if (response.isSuccess()) {
            response.parseData();
        }
        return response;
    }
    
    public static RefundSingleQueryResponse refundSingQuery(RefundSingleQueryParam refundSingleQueryParam, String secret) throws Exception {
        return refundSingQuery(REFUND_SINGLE_QUERY, refundSingleQueryParam, secret, RefundSingleQueryResponse.class);
    }
    
    public static RefundResponse refund(RefundParam refundParam, String secret) throws Exception {
        return refund(REFUND_URL, refundParam, secret, RefundResponse.class);
    }
    
    
}
    
