package com.aebiz.b2b2c.servicestaff.servicestaffsource.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.aebiz.b2b2c.baseframework.cache.BaseMemcachedCache;
import com.aebiz.b2b2c.baseframework.utils.StringUtil;
import com.aebiz.b2b2c.customermgr.common.CustomerCacheConstant;
import com.aebiz.b2b2c.customermgr.customer.vo.CustomerModel;
import com.aebiz.b2b2c.servicestaff.bankrelation.dao.BankRelationDAO;
import com.aebiz.b2b2c.servicestaff.bankrelation.vo.BankRelationModel;
import com.aebiz.b2b2c.servicestaff.common.ServicestaffCacheConstant;
import com.aebiz.b2b2c.servicestaff.doctorbill.vo.DoctorBillModel;
import com.aebiz.b2b2c.servicestaff.doctorbill.vo.DoctorBillQueryModel;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementModel;
import com.aebiz.b2b2c.servicestaff.packagemanagement.vo.PackageManagementQueryModel;
import com.aebiz.b2b2c.servicestaff.servicestaff.vo.ServicestaffModel;
import com.aebiz.b2b2c.servicestaff.servicestaffcomb.vo.ServicestaffcombModel;
import com.aebiz.b2b2c.servicestaff.servicestaffcomb.vo.ServicestaffcombQueryModel;
import com.aebiz.b2b2c.servicestaff.servicestaffcoord.vo.ServiceStaffCoordModel;
import com.aebiz.b2b2c.servicestaff.servicestaffcoord.vo.ServiceStaffCoordQueryModel;
import com.aebiz.b2b2c.servicestaff.servicestaffentry.vo.ServicestaffentryModel;
import com.aebiz.b2b2c.servicestaff.servicestaffentry.vo.ServicestaffentryQueryModel;
import com.aebiz.b2b2c.servicestaff.servicestaffinterview.vo.ServicestaffinterviewModel;
import com.aebiz.b2b2c.servicestaff.servicestaffinterview.vo.ServicestaffinterviewQueryModel;
import com.aebiz.b2b2c.servicestaff.servicestaffsource.vo.ServicestaffsourceModel;
import com.aebiz.b2b2c.servicestaff.servicestaffsource.vo.ServicestaffsourceQueryModel;
import com.danga.MemCached.MemCachedClient;

/**
 * 
 * @Description: (医生的缓存)    
 * @author XP  
 * @date 2015-12-29 上午11:06:05
 */
@Primary
@Repository
public class ServicestaffsourceCacheImpl extends
		BaseMemcachedCache<ServicestaffsourceModel,ServicestaffsourceQueryModel>
		implements ServicestaffsourceDAO {

	@Resource(name = ServicestaffCacheConstant.SERVICESTAFF_CLIENT_NAME)
	private MemCachedClient mcc;

	@Autowired
	private ServicestaffsourceDAO dao;

	@PostConstruct
	public void init() {
		this.setMcc(mcc);
		this.setDao(dao);
	}

	public ServicestaffsourceCacheImpl() {
		super(ServicestaffCacheConstant.SERVICESTAFF_SERVICESTAFFSOURCE_KEY);
	}
        
	
	
}