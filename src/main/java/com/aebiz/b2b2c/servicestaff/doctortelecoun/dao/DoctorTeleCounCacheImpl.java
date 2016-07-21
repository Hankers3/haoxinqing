package com.aebiz.b2b2c.servicestaff.doctortelecoun.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.servicestaff.common.ServicestaffCacheConstant;
import com.aebiz.b2b2c.servicestaff.doctorimport.vo.DoctorImportModel;
import com.aebiz.b2b2c.servicestaff.doctortelecoun.vo.DoctorTeleCounModel;
import com.aebiz.b2b2c.servicestaff.doctortelecoun.vo.DoctorTeleCounQueryModel;
import com.danga.MemCached.MemCachedClient;

/**
 * 
 * @Description: (医生的缓存)    
 * @author XP  
 * @date 2015-12-29 下午1:36:40
 */
@Primary
@Repository
public class DoctorTeleCounCacheImpl extends
		BaseMemcachedCache<DoctorTeleCounModel, DoctorTeleCounQueryModel> implements
		DoctorTeleCounDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private DoctorTeleCounDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public DoctorTeleCounCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_DOCTORTELECOUN_KEY);
	}

	
}
