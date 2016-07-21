package com.hxq.mobile.util.repository;

import com.wxcommon.repository.AbstractEntity;
import com.wxcommon.repository.SpringDao;
import com.wxcommon.util.MathUtils;
import com.wxcommon.util.ObjectUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import javax.annotation.Resource;


/**
 * 基于SpringJdbc的简单实体(单表)CURD接口实现
 *
 */
public abstract class WXDBSpringJdbcSimpleEntityService implements SimpleEntityService {
	/**
	 * 获取实体缓存区域名称
	 * @return
	 */
	protected abstract String getCacheName();
	/**
	 * 获取查询缓存区域名称
	 * @return
	 */
	protected abstract String getQueryCacheName();

	@Resource(name="WXDBRepository")
	protected SpringDao dao;

    @Resource(name="memcachedCacheManager")
    protected CacheManager cacheManager;

	@Override
	public int insert(AbstractEntity<?> bean) throws Exception {
		int i = MathUtils.toInt(dao.insert(bean, ""), -1);
		clearQueryCache();
		return i;
	}

	@Override
	public int delete(AbstractEntity<?> id) throws Exception {
		int i = dao.delete(id, "", getCache());
		clearQueryCache();
		return i;
	}

	@Override
	public int update(AbstractEntity<?> bean) throws Exception {
		int i = dao.update(bean, "", getCache());
		clearQueryCache();
		return i;
	}

	@Override
	public AbstractEntity<?> select(AbstractEntity<?> id) throws Exception {
		return dao.select(id, "", getCache());
	}

	/**
	 * 根据实体缓存区域名称获取缓存
	 * @return 指定的缓存
	 */
	protected Cache getCache() {
		return ObjectUtils.isEmpty(getCacheName())
				? null : cacheManager.getCache(getCacheName());
	}

	/**
	 * 根据查询缓存区域名称获取缓存
	 * @return 指定的缓存
	 */
	protected Cache getQueryCache() {
		return ObjectUtils.isEmpty(getQueryCacheName())
				? null : cacheManager.getCache(getQueryCacheName());
	}

	/**
	 * 清空查询缓存区域
	 */
	protected void clearQueryCache() {
		if(getQueryCache() != null) getQueryCache().clear();
	}
}
