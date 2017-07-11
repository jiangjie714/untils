package com.fufang.cloud.utils.wechat;

import com.fufang.cloud.utils.Md5Util;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 */
public class SignUtils {
    
    public static String md5(Map<String, String> data, String key) {
        String content = getSignContent(data);
        if (StringUtils.isBlank(content)) {
            return null;
        }
        return Md5Util.getMD5Str(content + "&key=" + key).toUpperCase();
    }
    
    public static String getSignContent(Map<String, String> data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        StringBuilder buf = new StringBuilder();
        TreeMap<String, String> map = new TreeMap<String, String>(data);
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            String k = entry.getKey();
            if (StringUtils.isBlank(k)) {
                continue;
            }
            if ("class".equalsIgnoreCase(k) || "key".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k) || "success".equalsIgnoreCase(k)
                    || "sign_type".equalsIgnoreCase(k) || "retcode".equalsIgnoreCase(k) || "retmsg".equalsIgnoreCase(k) || "input_charset".equalsIgnoreCase(k)) {
                continue;
            }
            String v = entry.getValue();
            if (StringUtils.isBlank(v)) {
                continue;
            }
            buf.append(k);
            buf.append("=");
            buf.append(v);
            buf.append("&");
        }
        
        buf = buf.deleteCharAt(buf.length() - 1);
        return buf.toString();
    }
    
    public static boolean checkMd5Sign(String content, String sign, String key) {
        if (StringUtils.isBlank(content)) {
            return false;
        }
        return Md5Util.getMD5Str(content + "&key=" + key,"GB2312").equalsIgnoreCase(sign);
    }
}
