package com.aebiz.b2b2c.servicestaff.telephonetime.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.servicestaff.common.ServicestaffCacheConstant;
import com.aebiz.b2b2c.servicestaff.telephonetime.vo.TelephoneTimeModel;
import com.aebiz.b2b2c.servicestaff.telephonetime.vo.TelephoneTimeQueryModel;
import com.danga.MemCached.MemCachedClient;

/**
 * 
 * @Description: (医生的缓存)    
 * @author XP  
 * @date 2015-12-29 上午11:06:05
 */
@Primary
@Repository
public class TelephoneTimeCacheImpl extends
		BaseMemcachedCache<TelephoneTimeModel,TelephoneTimeQueryModel>
		implements TelephoneTimeDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private TelephoneTimeDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}
	@Override
	public void update(TelephoneTimeModel m) {
		dao.update(m);
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_TELEPHONETIME_KEY+m.getUuid());
	}
	public TelephoneTimeCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_TELEPHONETIME_KEY);
	}
	
	
	
}
