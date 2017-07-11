package com.fufang.cloud.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SendMsgUtil {
	
	private static final String u1 = "http://222.73.117.156/msg/HttpSendSM?account=fufangkj&pswd=Tch123456&mobile=";
	private static final String fm2 = "&msg=%e6%82%a8%e5%a5%bd%ef%bc%8c%e4%b8%ba%e4%ba%86%e7%a1%ae%e4%bf%9d%e6%82%a8%e7%9a%84%e8%b4%a6%e5%8f%b7%e5%ae%89%e5%85%a8%ef%bc%8c%e8%af%b7%e5%b0%86%e9%aa%8c%e8%af%81%e7%a0%81%ef%bc%88";
	private static final String fm3 = "%29%E8%BE%93%E5%85%A5%E9%A1%B5%E9%9D%A2%E4%B8%AD%EF%BC%8C%E8%B0%A2%E8%B0%A2%EF%BC%81";
	private static final String u3 = "&needstatus=true";
	private static final String mobileValid = "^(((13[0-9])|(15([0-3]|[5-9]))|(18[0-9])|(14[5,7])|(17[0-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$";
	
	
	public static String sendCaptcha(String phoneNum) throws Exception {
		Pattern regex = Pattern.compile(mobileValid);
	    Matcher matcher = regex.matcher(phoneNum);
	    if(!(matcher.matches())){
	    	throw new Exception("手机号无效！");
	    }
		Random r = new Random();
		int c = r.nextInt(1000000);
		String secCode = Integer.toString(c);
		while (secCode.length() < 6) {
			secCode = "0" + secCode;
		}
		try {
			URL url1 = new URL(u1 + phoneNum + fm2 + secCode + fm3 + u3);
			HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
			BufferedReader in1 = null;
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent","Mozilla/4.0(compatible;MSIE)");
			conn.connect();
			in1 = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String result = "";
			String line1 = "";
			int count = 0;
			while ((line1 = in1.readLine()) != null) {
				if (count == 0) {
					result += line1;
					count++;
				} else {
					result += "," + line1;
				}
			}
			if (result != null && !result.equals("")) {
				String[] split = result.split(",");
				if (split.length > 2) {
					if (!(split[1].equals("0"))) 
					throw new Exception("发送验证码失败！");
				}
			} else {
				throw new Exception("发送验证码失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("发送手机验证码失败！");
		} 
		return secCode;
	}
	
	public static void sendMsg(String phoneNum, String content){
		Pattern regex = Pattern.compile(mobileValid);
	    Matcher matcher = regex.matcher(phoneNum);
	    try {
		    if(!(matcher.matches())){
		    	throw new Exception("手机号无效！");
		    }
			URL url1 = new URL(u1 + phoneNum + fm2 + content + fm3 + u3);
			HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
			BufferedReader in1 = null;
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent","Mozilla/4.0(compatible;MSIE)");
			conn.connect();
			in1 = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String result = "";
			String line1 = "";
			int count = 0;
			while ((line1 = in1.readLine()) != null) {
				if (count == 0) {
					result += line1;
					count++;
				} else {
					result += "," + line1;
				}
			}
			if (result != null && !result.equals("")) {
				String[] split = result.split(",");
				if (split.length > 2) {
					if (!(split[1].equals("0"))) 
						throw new Exception("发送信息失败！");
				}
			} else {
				throw new Exception("发送信息失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送个性化信息
	 */
	public static void sendCustomerMsg(String phoneNum, String content) {
		Pattern regex = Pattern.compile(mobileValid);
	    Matcher matcher = regex.matcher(phoneNum);
	    try {
		    if(!(matcher.matches())){
		    	throw new Exception("手机号无效！");
		    }
			URL url1 = new URL(u1 + phoneNum + "&msg=" + URLEncoder.encode(content, "UTF-8") + u3);
			HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
			BufferedReader in1 = null;
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent","Mozilla/4.0(compatible;MSIE)");
			conn.connect();
			in1 = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String result = "";
			String line1 = "";
			int count = 0;
			while ((line1 = in1.readLine()) != null) {
				if (count == 0) {
					result += line1;
					count++;
				} else {
					result += "," + line1;
				}
			}
			if (result != null && !result.equals("")) {
				String[] split = result.split(",");
				if (split.length > 2) {
					if (!(split[1].equals("0"))) 
						throw new Exception("发送信息失败！");
				}
			} else {
				throw new Exception("发送信息失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
