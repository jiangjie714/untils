package com.fufang.cloud.utils.tianfubao;

import com.fufang.cloud.utils.httpclient.OKHttpUtil;
import com.fufang.cloud.utils.wechat.Converters;
import com.fufang.cloud.utils.wechat.SignUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 */
public class PaySupport {
    private static Logger logger = LoggerFactory.getLogger(PaySupport.class);
    private static final String input_charset = "UTF-8";
    protected static <T extends Response> T pay(String payUrl, Param payParam, String secret, HttpServletRequest request, Class<T> responseType) throws Exception {
        Map<String, String> map = Converters.obj2Map(payParam);
        map.put("input_charset", input_charset);
        map.put("spbill_create_ip", InetAddress.getLocalHost().getHostAddress());
        map.put("tran_time", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        map.put("cur_type", "CNY");
        map.put("sign", SignUtils.md5(map, secret));
        return getResponse(payUrl, secret, map, responseType);
    }
    
    protected static <T extends Response> T close(String closeUrl, Param closeParam, String secret, Class<T> responseType) throws Exception {
        Map<String, String> map = Converters.obj2Map(closeParam);
        map.put("input_charset", input_charset);
        map.put("tran_time", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        map.put("sign", SignUtils.md5(map, secret));
        return getResponse(closeUrl, secret, map, responseType);
    }
    
    protected static <T extends Response> T paySingQuery(String paySingleQueryUrl, Param paySingleQueryParam, String secret, Class<T> responseType) throws Exception {
        Map<String, String> map = Converters.obj2Map(paySingleQueryParam);
        map.put("input_charset", input_charset);
        map.put("sign", SignUtils.md5(map, secret));
        return getResponse(paySingleQueryUrl, secret, map, responseType);
    }
    
    protected static <T extends Response> T refundSingQuery(String refundSingleQueryUrl, Param refundSingleQueryParam, String secret, Class<T> responseType) throws Exception {
        Map<String, String> map = Converters.obj2Map(refundSingleQueryParam);
        map.put("input_charset", input_charset);
        map.put("sign", SignUtils.md5(map, secret));
        return getResponse(refundSingleQueryUrl, secret, map, responseType);
    }
    
    protected static <T extends Response> T refund(String refundUrl, Param refundParam, String secret, Class<T> responseType) throws Exception {
        Map<String, String> map = Converters.obj2Map(refundParam);
        map.put("input_charset", input_charset);
        map.put("tran_time", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        map.put("sign", SignUtils.md5(map, secret));
        return getResponse(refundUrl, secret, map, responseType);
    }
    
    protected static <T extends Response> T getResponse(String url, String secret, Map<String, String> map, Class<T> responseType) throws Exception {
        String str = OKHttpUtil.Builder().getAndReturnStr(url + "?" + Converters.map2Query(map));
        logger.info("响应String{}", str);
        T response = Converters.xml2Obj(str, responseType);
        logger.debug("响应{}", response);
        String retcode = response.getRetcode();
        if ("00".equals(retcode)) {
            String signContent = SignUtils.getSignContent(Converters.obj2Map(response));
            String sign = response.getSign();
            logger.debug("签名内容:{},返回的签名:{}", signContent, sign);
            boolean pass = SignUtils.checkMd5Sign(signContent, sign, secret);
            if (pass) {
                response.setSuccess(true);
            } else {
                logger.warn("响应String{}", str);
                logger.warn("响应{}", response);
                logger.warn("签名内容:{},返回的签名:{}", signContent, sign);
                throw new IllegalStateException("签名验证失败");
            }
        } else if ("205235".equals(retcode) || "205237".equals(retcode) || "205423".equals(retcode)) { //[205235]交易处理中 [205237]订单已交易成功 205423
            response.setSuccess(true);
        } else if ("200001".equals(retcode)) { //系统错误

        } else {
            if (!("205201".equals(retcode) || "205202".equals(retcode) || "205203".equals(retcode) || "205204".equals(retcode) || "205205".equals(retcode) ||
                    "205206".equals(retcode) || "205207".equals(retcode) || "205208".equals(retcode) || "205209".equals(retcode) || "205210".equals(retcode) ||
                    "205211".equals(retcode) || "205212".equals(retcode) || "205213".equals(retcode) || "205214".equals(retcode) || "205215".equals(retcode) ||
                    "205216".equals(retcode) || "205217".equals(retcode) || "205218".equals(retcode) || "205219".equals(retcode) || "205220".equals(retcode) ||
                    "205221".equals(retcode) || "205222".equals(retcode) || "205223".equals(retcode) || "205227".equals(retcode) || "205228".equals(retcode) ||
                    "205229".equals(retcode) || "205230".equals(retcode) || "205231".equals(retcode) || "205232".equals(retcode) || "205233".equals(retcode) ||
                    "205234".equals(retcode) || "205235".equals(retcode) || "205236".equals(retcode) || "205237".equals(retcode) || "205238".equals(retcode) ||
                    "205239".equals(retcode) || "205240".equals(retcode) || "205241".equals(retcode) || "205242".equals(retcode) || "205243".equals(retcode) ||
                    "205244".equals(retcode) || "205245".equals(retcode) || "205246".equals(retcode) || "205247".equals(retcode) || "205248".equals(retcode) ||
                    "205260".equals(retcode)
            )) {//错误码不在文档里的也要轮询...
                response.setSuccess(true);
            }
        }
        return response;
        
    }
}
