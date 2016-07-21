package com.aebiz.b2b2c.servicestaff.doctorgroup.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.servicestaff.common.ServicestaffCacheConstant;
import com.aebiz.b2b2c.servicestaff.doctorgroup.vo.DoctorGroupModel;
import com.aebiz.b2b2c.servicestaff.doctorgroup.vo.DoctorGroupQueryModel;
import com.danga.MemCached.MemCachedClient;

/**
 * 
 * @Description: (医生的分组的缓存)    
 * @author XP  
 * @date 2015-12-29 上午11:06:05
 */
@Primary
@Repository
public class DoctorGroupCacheImpl extends
		BaseMemcachedCache<DoctorGroupModel, DoctorGroupQueryModel>
		implements DoctorGroupDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private DoctorGroupDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public DoctorGroupCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_DOCTORCATEGORY_KEY);
	}
	

	
}
