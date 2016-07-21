package com.hxq.mobile.util.mybatis;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;

import com.wxcommon.util.ObjectUtils;
import com.wxcommon.util.SpringUtils;

public class SpringCache implements Cache {
	Logger log = LoggerFactory.getLogger(SpringCache.class);

	private String id;
	private String cacheManagerId;
	private String queryCacheName;
	private CacheManager cacheManager;
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public SpringCache(final String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void putObject(Object key, Object value) {
		if(ObjectUtils.isEmpty(key) || ObjectUtils.isEmpty(value)) return;
		org.springframework.cache.Cache client = getCacheClient();
		if(client != null) client.put(key, value);
		if(client != null) log.debug(String.format("CacheName:%s, put key:%s", client.getName(), key));
	}

	@Override
	public Object getObject(Object key) {
		org.springframework.cache.Cache client = ObjectUtils.isEmpty(key) ? null : getCacheClient();
		org.springframework.cache.Cache.ValueWrapper value = client != null ? client.get(key) : null;
		if(client != null) log.debug(String.format("CacheName:%s, get key:%s", client.getName(), key));
    	return value != null ? value.get() : null;
	}

	@Override
	public Object removeObject(Object key) {
		if(ObjectUtils.isEmpty(key)) return null;
		org.springframework.cache.Cache client = getCacheClient();
		if(client != null) client.evict(key);
		if(client != null) log.debug(String.format("CacheName:%s, remove key:%s", client.getName(), key));
		return client != null ? key : null;
	}

	@Override
	public void clear() {
		org.springframework.cache.Cache client = getCacheClient();
		if(client != null) client.clear();
		if(client != null) log.debug(String.format("CacheName:%s, clear", client.getName()));
	}

	@Override
	public int getSize() {
		return Integer.MAX_VALUE;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

	public void setQueryCacheName(String queryCacheName) {
		this.queryCacheName = queryCacheName;
	}

	public void setCacheManager(String cacheManager) {
		this.cacheManagerId = cacheManager;
	}

	private org.springframework.cache.Cache getCacheClient() {
		if(ObjectUtils.isEmpty(queryCacheName)) return null;
		if(cacheManager != null) return cacheManager.getCache(queryCacheName);
		if(!ObjectUtils.isEmpty(cacheManagerId)) cacheManager = (CacheManager) SpringUtils.getBean(cacheManagerId);
		if(cacheManager == null) cacheManager = SpringUtils.getBean(CacheManager.class);
		return cacheManager != null ? cacheManager.getCache(queryCacheName) : null;
	}
}
