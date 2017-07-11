package com.fufang.cloud.utils.session;

import javax.servlet.http.HttpSession;

/**
 * 统一session工具 （注：session里面的东西，除了基本类型，对象都必须存储为json结构）
 *
 */
public class SessionUtil {
	private static final String FF_SESSION_PRE = "ff_";
	public static final String FF_SESSION_EMPTY = FF_SESSION_PRE + "empty";
	public static final String FF_SESSION_VALUE_TYPE = "_json";
	public static final String FF_USER_SESSION = "user" + FF_SESSION_VALUE_TYPE;

	/**
	 * 获取session id
	 * 
	 * @param session
	 * @return
	 */
	public static String getId(HttpSession session) {
		return session.getId();
	}

	/**
	 * 设置
	 * 
	 * @param session
	 * @param key
	 * @param value
	 */
	public static void setAttribute(HttpSession session, String key, String value) {
		session.setAttribute(FF_SESSION_PRE + key, value);
	}

	/**
	 * 读取
	 * 
	 * @param session
	 * @param key
	 * @return
	 */
	public static String getAttribute(HttpSession session, String key) {
		Object obj = session.getAttribute(FF_SESSION_PRE + key);
		if (obj != null) {
			return obj.toString();
		} else {
			return FF_SESSION_EMPTY;
		}
	}

	/**
	 * 读取用户session
	 * 
	 * @param session
	 * @return
	 */
	public static String getUser(HttpSession session) {
		return getAttribute(session, FF_SESSION_PRE + FF_USER_SESSION);
	}

	/**
	 * 删除
	 * 
	 * @param session
	 * @param key
	 */
	public static void removeAttribute(HttpSession session, String key) {
		session.removeAttribute(key);
	}

	/**
	 * 销毁
	 * 
	 * @param session
	 */
	public static void invalidate(HttpSession session) {
		session.invalidate();
	}

}
