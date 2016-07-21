package com.aebiz.b2b2c.servicestaff.telephonecost.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.servicestaff.common.ServicestaffCacheConstant;
import com.aebiz.b2b2c.servicestaff.telephonecost.vo.TelephoneCostModel;
import com.aebiz.b2b2c.servicestaff.telephonecost.vo.TelephoneCostQueryModel;
import com.danga.MemCached.MemCachedClient;

/**
 * 
 * @Description: (医生的缓存)    
 * @author XP  
 * @date 2015-12-29 上午11:06:05
 */
@Primary
@Repository
public class TelephoneCostCacheImpl extends
		BaseMemcachedCache<TelephoneCostModel,TelephoneCostQueryModel>
		implements TelephoneCostDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private TelephoneCostDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	@Override
	public void update(TelephoneCostModel m) {
		dao.update(m);
		this.mcc.delete(ServicestaffCacheConstant.SERVICESTAFF_TELEPHONECOST_KEY+m.getUuid());
	}
	public TelephoneCostCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_TELEPHONECOST_KEY);
	}
	
	
	
}
