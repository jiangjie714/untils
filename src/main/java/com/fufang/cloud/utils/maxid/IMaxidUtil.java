/**
 * 
 */
package com.fufang.cloud.utils.maxid;

/**
 * 获取当前的最大ID的工具类
 * 
 */
public interface IMaxidUtil {
	
	/**
	 * 获取单据号并使其增长
	 * 
	 * @param orderPrefix 单据前缀
	 * @param pharmacyId 药店ID
	 * @return 当前单据流水号
	 * 
	 * @see RedisMaxidUtil
	 * @see SpringRedisMaxidUtil
	 */
	int getAndIncreament(String orderPrefix, int pharmacyId);
}