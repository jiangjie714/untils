package com.fufang.cloud.utils.jackson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class JacksonUtilTest {
	private static JacksonUtil jacksonUtil;
	private static final String DEFAULTJSON = "{\"id\":1,\"name\":\"张三\",\"teliphone\":\"13412345678\"}";
	private static final String DEFAULTMAPJSON = "{\"user\":" + DEFAULTJSON + "}";
	private static User DEFAULTUSER;
	
	@BeforeClass
	public static void init() {
		DEFAULTUSER = new User();
		DEFAULTUSER.setId(1);
		DEFAULTUSER.setName("张三");
		DEFAULTUSER.setTeliphone("13412345678");
		
		jacksonUtil = JacksonUtil.Builder();
	}
	
	@Test
	public void obj2JsonTest() throws Exception {
		String json = jacksonUtil.obj2Json(DEFAULTUSER);
		Assert.assertEquals(DEFAULTJSON, json);
	}
	
	@Test
	public void json2ModelTest() throws Exception {
		User user = jacksonUtil.json2Model(DEFAULTJSON, User.class);
		Assert.assertEquals(DEFAULTUSER.getName(), user.getName());
	}
	
	@Test
	public void map2JsonTest() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", DEFAULTUSER);
		String json = jacksonUtil.map2Json(map);
		Assert.assertEquals(DEFAULTMAPJSON, json);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("id", 1);
		map2.put("name", "张三");
		map2.put("teliphone", "13412345678");
		String json2 = jacksonUtil.map2Json(map2);
		Assert.assertEquals(DEFAULTJSON, json2);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void json2MapTest() throws Exception {
		Map<String, Object> map = jacksonUtil.json2Map(DEFAULTJSON);
		Assert.assertEquals(DEFAULTUSER.getName(), map.get("name").toString());
		
		Map<String, Object> userMap = jacksonUtil.json2Map(DEFAULTMAPJSON);
		Assert.assertEquals(DEFAULTUSER.getName(), jacksonUtil.map2Model((Map<String, Object>)userMap.get("user"), User.class).getName());
	}
	
	@Test
	public void json2MapForClassTest() throws Exception {
		Map<String, String> map = jacksonUtil.json2Map("{\"name\":\"张三\",\"teliphone\":\"13412345678\"}", String.class);
		Assert.assertEquals(DEFAULTUSER.getName(), map.get("name"));
	}
	
	@Test
	public void json2ListTest() throws Exception {
		List<User> list = jacksonUtil.json2List("[" + DEFAULTJSON + "," + DEFAULTJSON + "]", User.class);
		Assert.assertEquals(2, list.size());
		Assert.assertEquals(DEFAULTUSER.getName(), list.get(0).getName());
		Assert.assertEquals(DEFAULTUSER.getName(), list.get(1).getName());
	}
	
	@Test
	public void map2ModelTest() throws Exception {
		Map<String, Object> map = jacksonUtil.model2Map(DEFAULTUSER);
		User user = jacksonUtil.map2Model(map, User.class);
		Assert.assertEquals(map.get("name"), user.getName());
	}
	
	@Test
	public void model2MapTest() throws Exception {
		Map<String, Object> map = jacksonUtil.model2Map(DEFAULTUSER);
		Assert.assertEquals(DEFAULTUSER.getName(), map.get("name"));
	}
}
