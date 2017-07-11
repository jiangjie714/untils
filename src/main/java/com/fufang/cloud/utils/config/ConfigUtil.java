package com.fufang.cloud.utils.config;

import java.io.File;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * 统一配置文件工具（注：暂时按项目组分成几个配置文件，包括公共、DS、CP单体、CP连锁、RI、SG）
 * 
 *
 */
public class ConfigUtil {
	private static String COMMON = "common"; // 公共配置文件
	private static String DS = "ds"; // ds team
	private static String CP_S = "cp_s"; // cp single team
	private static String CP_C = "cp_c"; // cp chain team
	private static String RI = "ri"; // ri team
	private static String SG = "sg"; // sg team

	private static String FUFANG_HOME = "FUFANG_HOME"; // 系统环境变量（如：FUFANG_HOME：D:\ffconf）
	private static String CONF_TYPE = ".conf"; // config file type

	/**
	 * 获取公共配置文件（common.conf)
	 * 
	 * @return
	 */
	public static Config loadConfigForCommon() {
		return ConfigFactory.parseFile(new File(getPath(COMMON)));
	}

	/**
	 * 获取DS配置文件（ds.conf)
	 * 
	 * @return
	 */
	public static Config loadConfigForDS() {
		return ConfigFactory.parseFile(new File(getPath(DS)));
	}

	/**
	 * 获取单体配置文件（cp_s.conf)
	 * 
	 * @return
	 */
	public static Config loadConfigForCP_S() {
		return ConfigFactory.parseFile(new File(getPath(CP_S)));
	}

	/**
	 * 获取连锁配置文件（cp_c.conf)
	 * 
	 * @return
	 */
	public static Config loadConfigForCP_C() {
		return ConfigFactory.parseFile(new File(getPath(CP_C)));
	}

	/**
	 * 获取RI配置文件（ri.conf)
	 * 
	 * @return
	 */
	public static Config loadConfigForRI() {
		return ConfigFactory.parseFile(new File(getPath(RI)));
	}

	/**
	 * 获取SG配置文件（sg.conf)
	 * 
	 * @return
	 */
	public static Config loadConfigForSG() {
		return ConfigFactory.parseFile(new File(getPath(SG)));
	}

	private static String getPath(String path) {
		return getBasePath() + File.separator + path + CONF_TYPE;
	}

	private static String getBasePath() {
		return System.getenv(FUFANG_HOME);
	}
}
