package com.hxq.mobile.util.repository;

import java.io.Serializable;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import com.hxq.mobile.util.mybatis.BaseMapper;
import com.hxq.mobile.util.mybatis.SqlHelper;
import com.wxcommon.repository.AbstractEntity;
import com.wxcommon.util.ObjectUtils;

/**
 * Created by Alice on 2016/4/21 0021.
 * 通用基础service实现类
 */
public abstract class MybatisBaseServiceImp<T, ID extends Serializable> implements MybatisBaseService<T, ID> {
	Logger log = LoggerFactory.getLogger(MybatisBaseServiceImp.class);

	@Resource(name="memcachedCacheManager")
    protected CacheManager cacheManager;

    private BaseMapper<T, ID> mapper;
    public void setBaseMapper(BaseMapper<T, ID> mapper) throws Exception{
        this.mapper = mapper;
    }

    @Override
    public int deleteByPrimaryKey(ID id) throws Exception {
    	evilCache(getCache(), makeEntityCacheKey(id));
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T record) throws Exception {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(T record) throws Exception {
        return mapper.insertSelective(record);
    }

    @Override
    public T selectByPrimaryKey(ID id) throws Exception {
    	String key = makeEntityCacheKey(id);
		T bean = getCache(getCache(), key);
		if(bean!=null) return bean;
		bean = mapper.selectByPrimaryKey(id);
		if(bean!=null) putCache(getCache(), key, bean);
		return bean;
    }

    @Override
    public int updateByPrimaryKeySelective(T record) throws Exception {
    	AbstractEntity<?> bean = (AbstractEntity<?>) record;
    	evilCache(getCache(), makeEntityCacheKey(bean.getId()));
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(T record) throws Exception {
    	AbstractEntity<?> bean = (AbstractEntity<?>) record;
    	evilCache(getCache(), makeEntityCacheKey(bean.getId()));
        return mapper.updateByPrimaryKey(record);
    }

	/**
	 * 根据实体缓存区域名称获取缓存
	 * @return 指定的缓存
	 */
	protected Cache getCache() {
		String nameSpace = SqlHelper.getMapperNameSpace(mapper);
		String cacheName = ObjectUtils.isEmpty(nameSpace) ? mapper.getClass().getName() : nameSpace;
		return ObjectUtils.isEmpty(cacheName) ? null : cacheManager.getCache(cacheName);
	}

	/**
	 * 产生数据模型对象的缓存键值
	 * @param bean 数据模型对象
	 * @return dao的命名:数据模型对象的id
	 */
	protected String makeEntityCacheKey(Serializable id) {
		return id.toString().replaceAll("\\s","").replaceAll("\t","")
				.replaceAll("\r","").replaceAll("\n","");
	}

	/**
	 * 放入缓存对象
	 * @param cache 缓存区
	 * @param key 缓存键值
	 * @param value 缓存对象
	 */
	private void putCache(Cache cache, String key, T value) {
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
    private T getCache(Cache cache, String key) {
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
    private void evilCache(Cache cache, String key) {
		if(cache==null || ObjectUtils.isEmpty(key)) return;
		log.debug(String.format("CacheName:%s, evict key:%s", cache.getName(), key));
		try {cache.evict(key);} catch (Exception e) {log.error(key, e);}
	}
}
