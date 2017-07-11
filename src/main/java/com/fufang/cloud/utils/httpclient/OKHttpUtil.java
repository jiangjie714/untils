package com.fufang.cloud.utils.httpclient;

import com.fufang.cloud.core.exception.BusinessException;
import com.fufang.cloud.core.response.FFApiResponse;
import com.fufang.cloud.utils.jackson.JacksonUtil;
import com.squareup.okhttp.*;
import com.squareup.okhttp.Request.Builder;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * OKHTTP工具类
 *
 */
public class OKHttpUtil {
	private static final String GET = "get";
	private static final String POST = "post";
	private static final String PUT = "put";
	private static final String DELETE = "delete";
	private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private static final MediaType XML = MediaType.parse("application/xml; charset=utf-8");
	
	private static final OKHttpUtil okhttpUtil = new OKHttpUtil();
	private static OkHttpClient client;
	
	private OKHttpUtil() {
		client = new OkHttpClient();
		client.setConnectTimeout(60, TimeUnit.SECONDS);
		client.setReadTimeout(5 * 60, TimeUnit.SECONDS);
		client.setWriteTimeout(5 * 60, TimeUnit.SECONDS);
		
//		ConnectionPool connectionPool = new ConnectionPool(10, 30 * 1000);
//		client.setConnectionPool(connectionPool);
	}
	
	public static final OKHttpUtil Builder() {
		return okhttpUtil;
	}
	
	public Object get(String url) throws Exception {
		return responseBody(url, GET, null);
	}
	
	public String getAndReturnJson(String url) throws Exception {
		Object obj = responseBody(url, GET, null);
		return obj == null ? null : JacksonUtil.Builder().obj2Json(obj);
	}
	
	public String getAndReturnStr(String url) throws IOException {
		Builder builder = new Builder().url(url);
		Response response = client.newCall(builder.build()).execute();
		if (response.isSuccessful()) {
			return response.body().string();
		} else {
			throw new RuntimeException("状态码:" + response.code() + ", message:" + response.message());
		}
	}
	
	public Object post(String url, String json) throws Exception {
		return responseBody(url, POST, json);
	}
	
	public String postAndReturnJson(String url, String json) throws Exception {
		Object obj = responseBody(url, POST, json);
		return obj == null ? null : JacksonUtil.Builder().obj2Json(obj);
	}
	
	public String postXmlAndReturnStr(String url, String xml) throws Exception {
		Builder builder = new Request.Builder().url(url);
		RequestBody body = null;
		if (xml != null) {
			body = RequestBody.create(XML, xml);
		}
		Request request = builder.post(body).build();
		Response response = client.newCall(request).execute();
		if (response.isSuccessful()) {
			return response.body().string();
		} else {
			throw new RuntimeException("状态码:" + response.code() + ", message:" + response.message());
		}
	}
	
	public String postJsonAndReturnStr(String url, String json) throws Exception {
		Builder builder = new Request.Builder().url(url);
		RequestBody body = null;
		if (json != null) {
			body = RequestBody.create(JSON, json);
		}
		Request request = builder.post(body).build();
		Response response = client.newCall(request).execute();
		if (response.isSuccessful()) {
			return response.body().string();
		} else {
			throw new RuntimeException("状态码:" + response.code() + ", message:" + response.message());
		}
	}
	
	public Object put(String url, String json) throws Exception {
		return responseBody(url, PUT, json);
	}
	
	public String putAndReturnJson(String url, String json) throws Exception {
		Object obj = responseBody(url, PUT, json);
		return obj == null ? null : JacksonUtil.Builder().obj2Json(obj);
	}
	
	public Object delete(String url) throws Exception {
		return responseBody(url, DELETE, null);
	}
	
	public String deleteAndReturnJson(String url) throws Exception {
		Object obj = responseBody(url, DELETE, null);
		return obj == null ? null : JacksonUtil.Builder().obj2Json(obj);
	}
	
	public Object delete(String url, String json) throws Exception {
		return responseBody(url, DELETE, json);
	}
	
	public String deleteAndReturnJson(String url, String json) throws Exception {
		Object obj = responseBody(url, DELETE, json);
		return obj == null ? null : JacksonUtil.Builder().obj2Json(obj);
	}
	
	private Object responseBody(String url, String type, String json) throws Exception {
		Builder builder = new Request.Builder().url(url);
		RequestBody body = null;
		Request request = null;
		Response response = null;
		Object value = null;
		String message = "";
		if (json != null) {
			body = RequestBody.create(JSON, json);
		}

		switch (type) {
		case GET:
			request = builder.build();
			break;
		case POST:
			request = builder.post(body).build();
			break;
		case PUT:
			request = builder.put(body).build();
			break;
		case DELETE:
			if(body == null) {
				request = builder.delete().build();
			} else {
				request = builder.delete(body).build();
			}
			break;
		default:
			break;
		}

		response = client.newCall(request).execute();
		if(response.isSuccessful()) {
			Map<String, Object> map = JacksonUtil.Builder().json2Map(response.body().string());
			String code = map.get("code").toString();
			if (FFApiResponse.SUCCESS.equals(code)) {
				value = map.get("data");
			} else if (FFApiResponse.EXCEPTION_BUSINESS.equals(code)) {
				message = map.get("message").toString();
				throw new BusinessException(message);
			} else {
				message = map.get("message").toString();
				throw new RuntimeException(message);
			}
		} else {
			throw new RuntimeException("状态码:" + response.code() + ", message:" + response.message());
		}

		return value;
	}
}
