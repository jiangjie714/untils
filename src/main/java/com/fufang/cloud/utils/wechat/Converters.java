package com.fufang.cloud.utils.wechat;

import com.fufang.cloud.utils.tianfubao.weChat.pay.PayParam;
import com.fufang.cloud.utils.tianfubao.weChat.paySingleQuery.PaySingleQueryResponse;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */
public class Converters {
    private static final Logger logger = LoggerFactory.getLogger(Converters.class);
    
    public static String map2Query(Map<String, String> params) {
        StringBuilder buf = new StringBuilder();
        Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            String k = entry.getKey();
            if (StringUtils.isBlank(k)){
                continue;
            }
            String v = entry.getValue();
            if (StringUtils.isBlank(v)){
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
    
    public static Map<String, String> obj2Map(Object obj) throws Exception {
        Map<String, String> result = new HashMap<>();
        BeanInfo info = Introspector.getBeanInfo(obj.getClass());
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            Method reader = pd.getReadMethod();
            if (reader != null) {
                String name = pd.getName();
                Object value = pd.getReadMethod().invoke(obj);
                if ("class".equals(name) || value == null || StringUtils.isBlank(value.toString())) {
                    continue;
                }
                result.put(name, String.valueOf(reader.invoke(obj)));
            }
        }
        return result;
    }
    
    public static <T> T xml2Obj(String xml,Class<T> resultType) {
        try {
            T result = resultType.newInstance();
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new StringReader(xml));
            Element root = document.getRootElement();
            List<Element> elements = root.elements();
            for (Element element : elements) {
                String elementName = element.getName();
                Field declaredField = ReflectionUtils.findField(resultType, elementName);
                if (declaredField != null) {
                    declaredField.setAccessible(true);
                    if (resultType.equals(PaySingleQueryResponse.class) || resultType.equals(com.fufang.cloud.utils.tianfubao.aliPay.paySingleQuery.PaySingleQueryResponse.class)) {
                        if (elementName.equals("data")) {
                            Matcher m = Pattern.compile("(?<=<data>)<record>.*?</record>(?=</data>)").matcher(xml);
                            if (m.find()) {
                                declaredField.set(result, m.group());
                            }
                        } else {
                            declaredField.set(result, element.getText());
                        }
                    } else {
                        declaredField.set(result, element.getText());
                    }
                }
            }
            return result;
        } catch (DocumentException | IllegalAccessException | InstantiationException e) {
            logger.error(e.getLocalizedMessage(),e);
        }
        return null;
    }
    
    public static Map<String,String> xml2Map(String xml) {
        Map<String, String> map = new HashMap<>();
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new StringReader(xml));
            Element root = document.getRootElement();
            List<Element> elements = root.elements();
            for (Element element : elements) {
                map.put(element.getName(), element.getText());
            }
            return map;
        } catch (DocumentException e) {
            logger.error(e.getLocalizedMessage(),e);
        }
        return null;
    }
    
    public static String map2Xml(Map<String, String> data){
        Document document = DocumentHelper.createDocument();
        Element nodeElement = document.addElement("xml");
        for (String key : data.keySet()) {
            Element keyElement = nodeElement.addElement(key);
            keyElement.setText(data.get(key));
        }
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            OutputFormat format = new OutputFormat("   ", true, "UTF-8");
            XMLWriter writer = new XMLWriter(out, format);
            writer.write(document);
            return out.toString("UTF-8");
        } catch (Exception ex) {
            logger.error(ex.getLocalizedMessage(), ex);
        }
        return null;
    }
    
    public static void main(String[] args) throws Exception {
        PayParam payParam = new PayParam();
        payParam.setBank_mch_id(33);
        payParam.setBank_mch_name("setBank_mch_name");
        payParam.setItem_name("itemName");
        payParam.setNotify_url("notifyurl");
        payParam.setSp_billno("billno");

        long time = System.currentTimeMillis();
        Map<String, String> map = obj2Map(payParam);
        System.out.println(map);
        long timeEnd = System.currentTimeMillis();
        System.out.println(timeEnd - time);
    
        Map<String, String> map1 = xml2Map("<root><name>zhifu</name></root>");
        System.out.println(map1);
    
        System.out.println(xml2Obj("<?xml version=\"1.0\" encoding=\"GB2312\" ?>\n" +
                "<root>\n" +
                "<data>\n" +
                "<![CDATA[<record>\n" +
                "<close_time></close_time>\n" +
                "<create_time>2017-02-25 10:21:47</create_time>\n" +
                "<item_attach></item_attach>\n" +
                "<item_name>测试药店姣100</item_name>\n" +
                "<listid>1021800776625170225000034218</listid>\n" +
                "<merch_listid>2017022505098299</merch_listid>\n" +
                "<pay_time>2017-02-25 10:21:48</pay_time>\n" +
                "<pay_type>800208</pay_type><sp_billno>20170225102214001592722690</sp_billno><spid>1800776625</spid><state>3</state><tran_amt>1</tran_amt></record>]]></data><retcode>00</retcode><retmsg>操作成功</retmsg><sign>a6e733640a312abd8f49a765a0947214</sign><sign_type>MD5</sign_type><spid>1800776625</spid></root>\n", PaySingleQueryResponse.class));
//
//        long time2 = System.currentTimeMillis();
//        Map<String, Object> map2 = JacksonUtil.Builder().model2Map(payParam);
//        System.out.println(map2);
//        long time2End = System.currentTimeMillis();
//        System.out.println(time2End - time2);

    }

}
