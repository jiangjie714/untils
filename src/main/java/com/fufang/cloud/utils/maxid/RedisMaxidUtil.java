/**
 * 
 */
package com.fufang.cloud.utils.maxid;

import java.text.SimpleDateFormat;
import java.util.Date;

import redis.clients.jedis.Jedis;

/**
 *
 */
public class RedisMaxidUtil implements IMaxidUtil {

	private Jedis jedis = null;
	private static final SimpleDateFormat FMT = new SimpleDateFormat("yyyyMMdd");
	private static final int INIT_ID = 1;
	private static final int DAY = 60 * 60 * 24;

	public RedisMaxidUtil(Jedis jedis) {
		this.jedis = jedis;
	}

	@Override
	protected void finalize() throws Throwable {
		if (jedis != null) {
			jedis.close();
		}
		super.finalize();
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

		long id = jedis.incr(key);
		if (id == INIT_ID)
			jedis.expire(key, DAY);

		return (int) id;
	}

}