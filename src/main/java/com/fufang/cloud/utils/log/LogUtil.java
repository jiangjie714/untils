package com.fufang.cloud.utils.log;

import org.slf4j.Logger;

import com.fufang.cloud.core.exception.BusinessException;

/**
 * 统一日志工具（注：有参数的请求，必须把参数序列化成json格式打印出来）
 * 
 */
public class LogUtil {
	/**
	 * 记录info级别的日志
	 * 
	 * @param logger
	 * @param message
	 */
	public static void infoLog(Logger logger, String message) {
		logger.info(loggerMessageFormat(message));
	}

	/**
	 * 记录info级别的日志，并且记录参数
	 * 
	 * @param logger
	 * @param message
	 * @param paramsJson
	 */
	public static void infoLog(Logger logger, String message, String paramsJson) {
		logger.info(loggerMessageFormat(message, paramsJson));
	}

	/**
	 * 记录warn级别日志
	 * 
	 * @param logger
	 * @param message
	 */
	public static void warnLog(Logger logger, String message) {
		logger.warn(loggerMessageFormat(message));
	}

	/**
	 * 记录warn级别日志
	 * 
	 * @param logger
	 * @param message
	 * @param e
	 */
	public static void warnLog(Logger logger, String message, Throwable e) {
		logger.warn(loggerMessageFormat(message), e);
	}

	/**
	 * 记录warn级别日志，并且记录参数
	 * 
	 * @param logger
	 * @param message
	 * @param paramsJson
	 */
	public static void warnLog(Logger logger, String message, String paramsJson) {
		logger.warn(loggerMessageFormat(message, paramsJson));
	}

	/**
	 * 记录warn级别日志，并且记录参数
	 * 
	 * @param logger
	 * @param message
	 * @param paramsJson
	 * @param e
	 */
	public static void warnLog(Logger logger, String message, String paramsJson, Throwable e) {
		logger.warn(loggerMessageFormat(message, paramsJson), e);
	}

	/**
	 * 记录error级别日志
	 * 
	 * @param logger
	 * @param message
	 */
	public static void errorLog(Logger logger, String message) {
		logger.error(loggerMessageFormat(message));
	}

	/**
	 * 记录error级别日志
	 * 
	 * @param logger
	 * @param message
	 * @param e
	 */
	public static void errorLog(Logger logger, String message, Throwable e) {
		logger.error(loggerMessageFormat(message), e);
	}

	/**
	 * 记录error级别日志，并且记录参数
	 * 
	 * @param logger
	 * @param message
	 * @param paramsJson
	 */
	public static void errorLog(Logger logger, String message, String paramsJson) {
		logger.error(loggerMessageFormat(message, paramsJson));
	}

	/**
	 * 记录error级别日志，并且记录参数
	 * 
	 * @param logger
	 * @param message
	 * @param paramsJson
	 * @param e
	 */
	public static void errorLog(Logger logger, String message, String paramsJson, Throwable e) {
		exceptionLog(logger, message, paramsJson, e);
	}

	private static String loggerMessageFormat(String message) {
		return "message:'" + message + "'";
	}

	private static String loggerMessageFormat(String message, String paramsJson) {
		return "message:'" + message + "',params:'" + paramsJson + "'";
	}

	private static void exceptionLog(Logger logger, String message, String paramsJson, Throwable ex) {
		// 日志记录，业务异常日志警告
		if (ex instanceof BusinessException) {
			logger.warn(loggerMessageFormat(message, paramsJson), ex);
		} else {
			// 非业务异常报警处理
			logger.error(loggerMessageFormat(message, paramsJson), ex);
		}
	}
}
