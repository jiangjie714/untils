/**
 * 
 */
package com.fufang.cloud.utils.maxid;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;

/**
 *
 */
public class SpringRedisMaxidUtil implements IMaxidUtil {

	private RedisTemplate<String, Integer> template = null;
	private static final SimpleDateFormat FMT = new SimpleDateFormat("yyyyMMdd");
	private static final int INIT_ID = 1;
	private static final int DAY = 60 * 60 * 24;

	public SpringRedisMaxidUtil(RedisTemplate<String, Integer> template) {
		this.template = template;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fufang.cloud.utils.maxid.IMaxidUtil#getAndIncreament(java.lang.
	 * String, int)
	 */
	@Override
	public int getAndIncreament(String orderPrefix, int pharmacyId) {
		String key = String.format("maxid-%d-%s-%s", pharmacyId, orderPrefix, FMT.format(new Date()));

		long id = template.opsForValue().increment(key, 1);
		if (id == INIT_ID)
			template.expire(key, DAY, TimeUnit.SECONDS);
		return (int) id;
	}

}