package com.fufang.cloud.utils.wechat;


import com.fufang.cloud.utils.RandomStringGenerator;
import com.fufang.cloud.utils.httpclient.OKHttpUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.Map;

/***
 * Create date:2015-01-19
 * <p>
 * 公众平台通用接口工具类
 * </p>
 * <hr>
 * Edit List:
 * <hr>
 * 
 * 
 */
public class WeChatUtil {
	private static Logger logger = LoggerFactory.getLogger(WeChatUtil.class);
	// 通过code换取网页授权access_token
	public static String request_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	// 刷新access_token（如果需要）
	public static String refresh_url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	// 拉取用户信息(需scope为 snsapi_userinfo)

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			ce.printStackTrace();
			logger.error("Weixin server connection timed out.");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("https request error:{}", e);
		}
		return jsonObject;
	}

	/**
	 * 获取网页授权凭证
	 * 
	 * @param appId
	 *            公众账号的唯一标识
	 * @param appSecret
	 *            公众账号的密钥
	 * @param code
	 * @return WeixinAouth2Token
	 */
	public static WeixinOauth2Token getOauth2AccessToken(String appId,
			String appSecret, String code) {
		WeixinOauth2Token wat = null;
		// 拼接请求地址
		String requestUrl = request_url;
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("SECRET", appSecret);
		requestUrl = requestUrl.replace("CODE", code);
		// 获取网页授权凭证
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				logger.error("获取网页授权凭证失败 errorCode:" + errorCode + "errorMsg:"
						+ errorMsg);
			}
		}
		return wat;
	}

	/**
	 * 刷新网页授权凭证
	 * 
	 * @param appId
	 *            公众账号的唯一标识
	 * @param refreshToken
	 * @return WeixinAouth2Token
	 */
	public static WeixinOauth2Token refreshOauth2AccessToken(String appId,
			String refreshToken) {
		WeixinOauth2Token wat = null;
		// 拼接请求地址
		String requestUrl = refresh_url;
		requestUrl = requestUrl.replace("APPID", appId);
		requestUrl = requestUrl.replace("REFRESH_TOKEN", refreshToken);
		// 刷新网页授权凭证
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				logger.error("刷新网页授权凭证失败errorCode:" + errorCode + "errorMsg:"
						+ errorMsg);
			}
		}
		return wat;
	}
	
	
	public static String getOpenIdByAuthCode(AuthParam authParam,String apiKey) throws Exception {
		Map<String, String> map = Converters.obj2Map(authParam);
		map.put("nonce_str", RandomStringGenerator.randomString(32));
		map.put("sign", SignUtils.md5(map, apiKey));
		String str = OKHttpUtil.Builder().postXmlAndReturnStr("https://api.mch.weixin.qq.com/tools/authcodetoopenid", Converters.map2Xml(map));
		AuthResponse authResponse = Converters.xml2Obj(str, AuthResponse.class);
		logger.info("getOpenIdByAuthCode返回结果{}", authResponse);
		if ("SUCCESS".equalsIgnoreCase(authResponse.getReturn_code()) && "SUCCESS".equalsIgnoreCase(authResponse.getResult_code())) {
			return authResponse.getOpenid();
		}
		return "";
	}
	
	public static class AuthParam{
		private String appid;
		private String mch_id;
		private String auth_code;
		
		public AuthParam(String appid, String mch_id, String auth_code) {
			this.appid = appid;
			this.mch_id = mch_id;
			this.auth_code = auth_code;
		}
		
		public AuthParam() {
			
		}
		
		public String getAppid() {
			return appid;
		}
		
		public void setAppid(String appid) {
			this.appid = appid;
		}
		
		public String getMch_id() {
			return mch_id;
		}
		
		public void setMch_id(String mch_id) {
			this.mch_id = mch_id;
		}
		
		public String getAuth_code() {
			return auth_code;
		}
		
		public void setAuth_code(String auth_code) {
			this.auth_code = auth_code;
		}
	}
	
	private static class AuthResponse {
		private String return_code;
		private String return_msg;
		private String appid;
		private String mch_id;
		private String nonce_str;
		private String sign;
		private String result_code;
		private String err_code;
		private String openid;
		
		public AuthResponse() {
		}
		
		public String getReturn_code() {
			return return_code;
		}
		
		public void setReturn_code(String return_code) {
			this.return_code = return_code;
		}
		
		public String getReturn_msg() {
			return return_msg;
		}
		
		public void setReturn_msg(String return_msg) {
			this.return_msg = return_msg;
		}
		
		public String getAppid() {
			return appid;
		}
		
		public void setAppid(String appid) {
			this.appid = appid;
		}
		
		public String getMch_id() {
			return mch_id;
		}
		
		public void setMch_id(String mch_id) {
			this.mch_id = mch_id;
		}
		
		public String getNonce_str() {
			return nonce_str;
		}
		
		public void setNonce_str(String nonce_str) {
			this.nonce_str = nonce_str;
		}
		
		public String getSign() {
			return sign;
		}
		
		public void setSign(String sign) {
			this.sign = sign;
		}
		
		public String getResult_code() {
			return result_code;
		}
		
		public void setResult_code(String result_code) {
			this.result_code = result_code;
		}
		
		public String getErr_code() {
			return err_code;
		}
		
		public void setErr_code(String err_code) {
			this.err_code = err_code;
		}
		
		public String getOpenid() {
			return openid;
		}
		
		public void setOpenid(String openid) {
			this.openid = openid;
		}
		
		@Override
		public String toString() {
			return "AuthResponse{" +
					"return_code='" + return_code + '\'' +
					", return_msg='" + return_msg + '\'' +
					", appid='" + appid + '\'' +
					", mch_id='" + mch_id + '\'' +
					", nonce_str='" + nonce_str + '\'' +
					", sign='" + sign + '\'' +
					", result_code='" + result_code + '\'' +
					", err_code='" + err_code + '\'' +
					", openid='" + openid + '\'' +
					'}';
		}
	}

}