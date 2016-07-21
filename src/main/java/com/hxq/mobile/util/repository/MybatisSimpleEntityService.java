package com.hxq.mobile.util.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import com.hxq.mobile.util.mybatis.PagingUtils;
import com.hxq.mobile.util.mybatis.SqlHelper;
import com.wxcommon.repository.AbstractEntity;
import com.wxcommon.util.ObjectUtils;

/**
 * 基于mybatis的简单实体(单表)CURD接口实现
 *
 */
public abstract class MybatisSimpleEntityService implements SimpleEntityService {
	Logger log = LoggerFactory.getLogger(MybatisSimpleEntityService.class);

	/**
     * 定义成抽象方法,由子类实现,完成dao的注入
     * @return SimpleEntityDao实现类
     */
    protected abstract SimpleEntityDao getDao();

	/**
	 * 获取实体缓存区域名称
	 * @return
	 */
	protected abstract String getCacheName();

    @Resource(name="memcachedCacheManager")
    protected CacheManager cacheManager;

	@Override
	public int insert(AbstractEntity<?> bean) {
		return getDao().insert(bean);
	}

	@Override
	public int delete(AbstractEntity<?> id) {
		evilCache(getCache(), makeEntityCacheKey(id));
		return getDao().delete(id);
	}

	@Override
	public int update(AbstractEntity<?> bean) {
		evilCache(getCache(), makeEntityCacheKey(bean));
		return getDao().update(bean);
	}

	@Override
	public AbstractEntity<?> select(AbstractEntity<?> id) {
		String key = makeEntityCacheKey(id);
		AbstractEntity<?> bean = getCache(getCache(), key);
		if(bean!=null) return bean;
		bean = getDao().select(id);
		if(bean!=null) putCache(getCache(), key, bean);
		return bean;
	}

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> form, Boolean isPagination) {
		if(isPagination != null && isPagination) {
			PagingUtils.startPage(form);//设置分页
			List<Map<String, Object>> lst = getDao().selectList(form);
			return PagingUtils.endPage(form, lst);//对结果进行包装，回填分页属性
		} else {
			return getDao().selectList(form);
		}
	}

	/**
	 * 产生数据模型对象的缓存键值
	 * @param bean 数据模型对象
	 * @return dao的命名:数据模型对象的id
	 */
	protected String makeEntityCacheKey(AbstractEntity<?> bean) {
		if(bean==null) return null;
		String namespace = SqlHelper.getMapperNameSpace(getDao());
		StringBuffer sbf = new StringBuffer(1000);
		if(ObjectUtils.isEmpty(namespace)) {
			sbf.append(bean.getId());
		} else {
			sbf.append(namespace).append(":").append(bean.getId());
		}
		return sbf.toString().replaceAll("\\s","").replaceAll("\t","")
				.replaceAll("\r","").replaceAll("\n","");
	}

	/**
	 * 根据实体缓存区域名称获取缓存
	 * @return 指定的缓存
	 */
	protected Cache getCache() {
		return ObjectUtils.isEmpty(getCacheName()) ? null : cacheManager.getCache(getCacheName());
	}

	/**
	 * 放入缓存对象
	 * @param cache 缓存区
	 * @param key 缓存键值
	 * @param value 缓存对象
	 */
	private void putCache(Cache cache, String key, Object value) {
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
    private <T> T getCache(Cache cache, String key) {
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
