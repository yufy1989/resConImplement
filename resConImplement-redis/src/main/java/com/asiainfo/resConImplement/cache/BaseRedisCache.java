package com.asiainfo.resConImplement.cache;

import org.springframework.beans.factory.annotation.Autowired;

import com.asiainfo.resConImplement.modules.mapper.JsonMapper;
import com.asiainfo.resConImplement.modules.nosql.redis.JedisClusterFactory;

/**
* 类说明：redis操作基础类
* @author 付和平
* @date 2016年6月12日 下午4:40:30
*/
public abstract class BaseRedisCache {
	protected JsonMapper jsonMapper = new JsonMapper();
	@Autowired
	protected JedisClusterFactory jedisCluster;
	
	/*
	 * 简单保存
	 */
	protected void save(String keyid,String value) throws Exception{
		jedisCluster.getObject().set(keyid, value);
	}
	/*
	 * 简单读取，根据key
	 */
	protected String  get(String keyid) throws Exception{
		return jedisCluster.getObject().get(keyid);
	}
	/*
	 * 简单删除，根据key
	 */
	protected void del(String keyid) throws Exception{
		jedisCluster.getObject().del(keyid);
	}

}
