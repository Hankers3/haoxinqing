package com.hxq.mobile.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;

import com.wxcommon.util.ObjectUtils;

public class CacheUtils {
	private static Logger log = LoggerFactory.getLogger(CacheUtils.class);

	/**
	 * 放入缓存对象
	 * @param cache 缓存区
	 * @param key 缓存键值
	 * @param value 缓存对象
	 */
	public static void putCache(Cache cache, String key, Object value) {
		if(cache==null || ObjectUtils.isEmpty(key) || ObjectUtils.isEmpty(value)) return;
		log.debug(String.format("CacheName:%s, put key:%s", cache.getName(), key));
		try {cache.put(key, value);} catch (Exception e) {log.error(key, e);}
	}

    /**
     * 获取缓存对象
     * @param cache 缓存区
     * @param key 缓存键值
     * @return 缓存对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getCache(Cache cache, String key) {
    	if(cache==null || ObjectUtils.isEmpty(key)) return null;
        log.debug(String.format("CacheName:%s, get key:%s", cache.getName(), key));
        Cache.ValueWrapper value = null;
        try {value = cache.get(key);} catch (Exception e) {log.error(key, e);}
    	return value != null ? (T) value.get() : null;
    }

	/**
	 * 失效缓存
	 * @param cache 缓存区
	 * @param key 缓存键值
	 */
    public static void evilCache(Cache cache, String key) {
		if(cache==null || ObjectUtils.isEmpty(key)) return;
		log.debug(String.format("CacheName:%s, evict key:%s", cache.getName(), key));
		try {cache.evict(key);} catch (Exception e) {log.error(key, e);}
	}
}
